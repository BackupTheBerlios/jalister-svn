package directorylister.gui.components.selectedfilespanel;

import directorylister.model.FileEntry;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:22:57
 */
final class JaListerDatabaseListenerAdapter extends directorylister.controllers.JaListerDatabaseListenerAdapter {
    /**
     * Field files
     */
    private final JList files;

    /**
     * Constructor JaListerDatabaseListenerAdapter creates a new JaListerDatabaseListenerAdapter instance.
     *
     * @param files of type JList
     */
    public JaListerDatabaseListenerAdapter(final JList files) {
        this.files = files;
    }

    /**
     * {@inheritDoc}
     *
     * @see directorylister.controllers.JaListerDatabaseListenerAdapter#notifyFileEntryRightClicked(FileEntry)
     */
    @Override
    public void notifyFileEntryRightClicked(final FileEntry fileEntry) {
        final DefaultListModel listModel = (DefaultListModel) files.getModel();
        if (listModel.contains(fileEntry)) {
            listModel.removeElement(fileEntry);
        }
        else {
            listModel.addElement(fileEntry);
        }
    }
}
