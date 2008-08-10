package de.berlios.jalister.gui.components.fileentrytree;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.resources.ResourceHandler;
import de.berlios.jalister.search.SearchResult;
import de.berlios.jalister.search.Searcher;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
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
        final JTree tree = new TooltipTree();
        tree.setName("FileTree");
        tree.setToggleClickCount(1);

        tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
        treeUpdater = new TreeUpdater(tree);
        JaListerDatabaseController.getInstance().addListener(treeUpdater);

        tree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(final TreeSelectionEvent e) {
                final TreePath path = e.getNewLeadSelectionPath();
                if (path != null) {
                    final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    final Object userObject = node.getUserObject();
                    if (userObject instanceof FileEntry) {
                        final FileEntry fileEntry = (FileEntry) userObject;
                        JaListerDatabaseController.getInstance().fileEntrySelected(fileEntry);
                    }
                }
            }
        });

        tree.addMouseListener(new TreeMouseListener(tree));
        tree.setCellRenderer(new TreeCellRenderer());


        setLayout(new BorderLayout());
        add(new JScrollPane(tree), BorderLayout.CENTER);

        searchBox = new JTextField();
        searchBox.setName("FileEntryTree.SearchBox");


        searchButton = new JButton();
        searchButton.setName("FileEntryTree.SearchButton");
        searchButton.setEnabled(false);
        searchButton.addActionListener(new SearchAction());
        final JPanel searchBar = new JPanel();
        final BoxLayout layout = new BoxLayout(searchBar, BoxLayout.X_AXIS);
        searchBar.setLayout(layout);

        searchBar.add(searchBox);
        searchBar.add(searchButton);

        add(searchBar, BorderLayout.NORTH);

        tree.setEnabled(false);

        searchBox.getDocument().addDocumentListener(new ButtonStateUpdater());

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

    /**
     * Class SearchAction ...
     *
     * @author schakal
     *         Created on 01.09.2007
     */
    private class SearchAction implements ActionListener {
        /**
         * {@inheritDoc}
         */
        public void actionPerformed(final ActionEvent e) {
            final JaListerDatabase oldDatabase = JaListerDatabaseController.getInstance().getCurrentDatabase();
            final Searcher searcher = oldDatabase.getService(Searcher.class);
            final String condition = searchBox.getText();

            final SearchResult searchResult = searcher.search(condition);
            updateTree(searchResult);

            final String tooltip = ResourceHandler.getInstance().getFormattedMessage("SearchResult.Tooltip",
                    searchResult.getResultCount(), searchResult.getSearchTime());
            searchBox.setToolTipText(tooltip);
        }

        /**
         * Method updateTree ...
         *
         * @param searchResult of type SearchResult
         */
        private void updateTree(final SearchResult searchResult) {
            treeUpdater.updateTree(searchResult.getRoot());
        }

    }

    /**
     * Class ButtonStateUpdater ...
     *
     * @author schakal
     *         Created on 01.09.2007
     */
    private class ButtonStateUpdater implements DocumentListener {

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
            searchButton.setEnabled(StringUtils.isNotEmpty(searchBox.getText()) && searchBox.isFocusOwner()
                    && JaListerDatabaseController.getInstance().getCurrentDatabase() != null);
        }

        /**
         * {@inheritDoc}
         */
        public void changedUpdate(final DocumentEvent e) {
            updateButtonState();
        }
    }
}
