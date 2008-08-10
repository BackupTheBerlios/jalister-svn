package de.berlios.jalister.gui.components.fileentrytree;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.resources.ResourceHandler;
import de.berlios.jalister.search.SearchResult;
import de.berlios.jalister.search.Searcher;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: schakal
 * Date: 10.08.2008
 * Time: 20:26:54
 * To change this template use File | Settings | File Templates.
 */
public class SearchPanel extends JPanel {

    /**
     * Field searchBox
     */
    private final JTextField searchBox;
    /**
     * Field searchButton
     */
    private final JButton searchButton;
    private TreeUpdater treeUpdater;

    public SearchPanel(TreeUpdater treeUpdater) {
        this.treeUpdater = treeUpdater;
        searchBox = new JTextField();
        searchBox.setName("FileEntryTree.SearchBox");
        // TODO: rewrite, when ShortcutManager ready!
        searchBox.addKeyListener(new SearchOnEnterKeyAdapter());

        searchButton = new JButton();
        searchButton.setName("FileEntryTree.SearchButton");
        searchButton.setEnabled(false);
        searchButton.addActionListener(new SearchAction());
        final BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);

        add(searchBox);
        add(searchButton);

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

    private class SearchOnEnterKeyAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                searchButton.doClick();
            }
        }
    }
}

