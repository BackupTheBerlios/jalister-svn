package directorylister.gui.components.selectedfilespanel;

import directorylister.model.FileEntry;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:22:57
 */
class FileEntryListenerAdapter extends directorylister.controllers.FileEntryListenerAdapter {
    private JList files;

    public FileEntryListenerAdapter(final JList files) {
        this.files = files;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyFileEntryToSelect(final FileEntry fileEntry) {
        final DefaultListModel listModel = (DefaultListModel) files.getModel();
        if (listModel.contains(fileEntry)) {
            listModel.removeElement(fileEntry);
        }
        else {
            listModel.addElement(fileEntry);
        }

    }
}
