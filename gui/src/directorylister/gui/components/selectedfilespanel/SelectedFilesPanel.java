package directorylister.gui.components.selectedfilespanel;

import directorylister.controllers.FileEntryController;
import directorylister.controllers.FileEntryListener;
import directorylister.gui.actions.SaveSelectedFilesAction;

import javax.swing.*;
import java.awt.BorderLayout;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 15:12:34
 */
public class SelectedFilesPanel extends JPanel {

    private JList files;

    /**
     * Constructs a new SelectedFilesPanel.
     */
    public SelectedFilesPanel() {
        setName("SelectedFilesPanel");

        files = new JList();
        files.setModel(new DefaultListModel());

        setLayout(new BorderLayout());

        add(new JScrollPane(files), BorderLayout.CENTER);

        JPanel toolBar = new JPanel();
        add(toolBar, BorderLayout.SOUTH);
        final JButton saveSelection = new JButton();
        saveSelection.setName("SelectedFilesPanel.SaveSelection");
        // TODO
        saveSelection.setText("SelectedFilesPanel.SaveSelection");
        saveSelection.addActionListener(new SaveSelectedFilesAction(files));

        toolBar.add(saveSelection);

        files.setCellRenderer(new ListCellRenderer());

        FileEntryListener listener = new FileEntryListenerAdapter(files);
        FileEntryController.getInstance().addListener(listener);

        files.addMouseListener(new SelectedFilesListMouseAction(files));
    }

}
