package directorylister.gui.components.selectedfilespanel;

import directorylister.model.FileEntry;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:25:33
 */
public class SelectedFilesListMouseAction extends MouseAdapter {
    private final JList files;

    public SelectedFilesListMouseAction(final JList files) {
        this.files = files;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (1 == e.getClickCount() && e.getButton() == MouseEvent.BUTTON3) {
            final FileEntry fileEntry = (FileEntry) files.getSelectedValue();
            if (fileEntry != null) {
                final DefaultListModel listModel = (DefaultListModel) files.getModel();
                listModel.removeElement(fileEntry);
            }

        }
    }
}
