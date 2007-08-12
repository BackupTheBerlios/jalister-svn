package directorylister.gui.components.fileentrytree;

import directorylister.controllers.FileEntryController;
import directorylister.model.FileEntry;
import directorylister.resources.ResourceHandler;
import directorylister.search.Searcher;
import org.apache.commons.lang.StringUtils;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * File Entry tree.
 *
 * @author Oleg Atamanenko.
 */
public final class FileEntryTree extends JPanel {

    /**
     * Field tree
     */
    private final JTree tree;
    /**
     * Field searchBox
     */
    private final JTextField searchBox;
    /**
     * Field searchButton
     */
    private final JButton searchButton;


    /**
     * Constructs a new FileEntryTree.
     */
    public FileEntryTree() {
        tree = new JTree();
        tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
        final FileEntryListener fileEntryListener = new FileEntryListener(tree);
        FileEntryController.getInstance().addListener(fileEntryListener);
        tree.setName("FileTree");


        tree.addMouseListener(new TreeMouseListener(tree));
        tree.setCellRenderer(new TreeCellRenderer());

        setLayout(new BorderLayout());
        add(new JScrollPane(tree), BorderLayout.CENTER);

        searchBox = new JTextField();
        searchBox.setName("FileEntryTree.SearchBox");


        searchButton = new JButton();
        searchButton.setName("FileEntryTree.SearchButton");
        searchButton.setEnabled(false);
        searchButton.addActionListener(new ActionListener() {
            /**
             * Field oldFileEntry
             */
            public FileEntry oldFileEntry;

            /**
             * {@inheritDoc}
             */
            public void actionPerformed(final ActionEvent e) {
                final Searcher searcher = new Searcher();
                final String condition = searchBox.getText();
                if (condition.equals(" ")) {
                    FileEntryController.getInstance().setCurrentFileEntry(oldFileEntry);
                } else {
                    if (null == oldFileEntry) {
                        oldFileEntry = FileEntryController.getInstance().getCurrentEntry();
                    }
                    final FileEntry fileEntry = searcher.search(oldFileEntry, condition);
                    FileEntryController.getInstance().setCurrentFileEntry(fileEntry);
                }

            }
        });
        final JPanel searchBar = new JPanel();
        final BoxLayout layout = new BoxLayout(searchBar, BoxLayout.X_AXIS);
        searchBar.setLayout(layout);

        searchBar.add(searchBox);
        searchBar.add(searchButton);

        add(searchBar, BorderLayout.NORTH);

        tree.setEnabled(false);

        searchBox.getDocument().addDocumentListener(new DocumentListener() {

            /**
             * {@inheritDoc}
             */
            public void insertUpdate(final DocumentEvent e) {
                updateButtonState();
            }

            /**
             * {@inheritDoc}
             */
            public void removeUpdate(final DocumentEvent e) {
                updateButtonState();
            }

            /**
             * Method updateButtonState ...
             */
            private void updateButtonState() {
                searchButton.setEnabled(StringUtils.isNotEmpty(searchBox.getText()) && searchBox.isFocusOwner());
            }

            /**
             * {@inheritDoc}
             */
            public void changedUpdate(final DocumentEvent e) {
                updateButtonState();
            }
        });

        searchBox.addFocusListener(new SearchBoxFocusListener(searchBox.getForeground()));
        searchBox.setForeground(searchBox.getDisabledTextColor());
    }

    /**
     * Class SearchBoxFocusListener ...
     *
     * @author schakal
     *         Created on 05.08.2007
     */
    private class SearchBoxFocusListener implements FocusListener {
        /**
         * Field color
         */
        private Color color;

        /**
         * Constructor SearchBoxFocusListener creates a new SearchBoxFocusListener instance.
         *
         * @param enabledTextColor of type Color
         */
        public SearchBoxFocusListener(final Color enabledTextColor) {
            color = enabledTextColor;
        }

        /**
         * {@inheritDoc}
         */
        public void focusGained(final FocusEvent e) {
            searchBox.setForeground(color);
            if (searchBox.getText().equals(ResourceHandler.getInstance().getMessage(searchBox.getName()))) {
                searchBox.setText("");
            }
        }

        /**
         * {@inheritDoc}
         */
        public void focusLost(final FocusEvent e) {
            if (StringUtils.isEmpty(searchBox.getText())) {
                searchBox.setText(ResourceHandler.getInstance().getMessage(searchBox.getName()));
                color = searchBox.getForeground();
                searchBox.setForeground(searchBox.getDisabledTextColor());
            }
        }
    }
}
