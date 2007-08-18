package directorylister.gui.components.fileentrytree;

import directorylister.controllers.JaListerDatabaseController;
import directorylister.model.FileEntry;
import directorylister.model.JaListerDatabase;
import directorylister.resources.ResourceHandler;
import directorylister.search.SearchResult;
import directorylister.search.Searcher;
import org.apache.commons.lang.StringUtils;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;

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
     * Field treeUpdater
     */
    private final TreeUpdater treeUpdater;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -7795558676747892696L;


    /**
     * Constructs a new FileEntryTree.
     */
    public FileEntryTree() {
        tree = new JTree() {
            public String getToolTipText(final MouseEvent event) {
                final Point point = event.getPoint();
                final TreePath path = tree.getPathForLocation((int) point.getX(), (int) point.getY());
                if (null != path) {
                    final Object pathComponent = path.getLastPathComponent();
                    if (null != pathComponent && pathComponent instanceof DefaultMutableTreeNode) {
                        final Object userObject = ((DefaultMutableTreeNode) pathComponent).getUserObject();
                        if (userObject instanceof FileEntry) {
                            final FileEntry entry = (FileEntry) userObject;
                            return entry.getFileName();
                        }
                        return userObject.toString();
                    }
                }
                return super.getToolTipText(event);
            }
        };

        final ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
        toolTipManager.setEnabled(true);
        toolTipManager.setInitialDelay(50);
        toolTipManager.registerComponent(tree);
        toolTipManager.setLightWeightPopupEnabled(true);

        tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
        treeUpdater = new TreeUpdater(tree);
        JaListerDatabaseController.getInstance().addListener(treeUpdater);
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
             * {@inheritDoc}
             */
            public void actionPerformed(final ActionEvent e) {
                final JaListerDatabase oldDatabase = JaListerDatabaseController.getInstance().getCurrentDatabase();
                final Searcher searcher = oldDatabase.getService(Searcher.class);
                final String condition = searchBox.getText();

                final SearchResult searchResult = searcher.search(condition);
                updateTree(searchResult);

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
     * Method updateTree ...
     *
     * @param searchResult of type SearchResult
     */
    private void updateTree(final SearchResult searchResult) {
        treeUpdater.updateTree(searchResult.getRoot());
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
