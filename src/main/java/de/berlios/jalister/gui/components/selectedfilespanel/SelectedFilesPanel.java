package de.berlios.jalister.gui.components.selectedfilespanel;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.controllers.JaListerDatabaseListener;
import de.berlios.jalister.gui.actions.io.SaveSelectedFilesAction;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 15:12:34
 */
public final class SelectedFilesPanel extends JPanel {

    /**
     * Field files
     */
    private final JList files;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -475665762723474676L;

    /**
     * Constructs a new SelectedFilesPanel.
     */
    public SelectedFilesPanel() {
        setName("SelectedFilesPanel");

        files = new JList();
        files.setModel(new DefaultListModel());
        files.setEnabled(false);
        setLayout(new BorderLayout());

        add(new JScrollPane(files), BorderLayout.CENTER);

        final JPanel toolBar = new JPanel();
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(toolBar, BorderLayout.SOUTH);

        final JButton saveSelection = new JButton();
        saveSelection.setName("SelectedFilesPanel.SaveSelection");
        saveSelection.addActionListener(new SaveSelectedFilesAction(files));
        saveSelection.setEnabled(false);
        toolBar.add(saveSelection);

        files.setCellRenderer(new ListCellRenderer());

        final JaListerDatabaseListener listener = new JaListerDatabaseListenerAdapter(files);
        JaListerDatabaseController.getInstance().addListener(listener);

        files.addMouseListener(new SelectedFilesListMouseAction(files));

        files.getModel().addListDataListener(new ListDataListener() {

            /**
             * {@inheritDoc}
             */
            public void intervalAdded(final ListDataEvent e) {
                checkModel();
            }

            /**
             * Method checkModel ...
             */
            private void checkModel() {
                final ListModel listModel = files.getModel();
                final boolean isEmpty = listModel.getSize() == 0;
                files.setEnabled(!isEmpty);
                saveSelection.setEnabled(!isEmpty);

            }

            /**
             * {@inheritDoc}
             */
            public void intervalRemoved(final ListDataEvent e) {
                checkModel();
            }

            /**
             * {@inheritDoc}
             */
            public void contentsChanged(final ListDataEvent e) {
                checkModel();
            }
        });
    }

}
