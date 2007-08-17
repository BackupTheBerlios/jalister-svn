package directorylister.gui.components.selectedfilespanel;

import directorylister.model.FileEntry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:22:57
 */
final class FileEntryListenerAdapter extends directorylister.controllers.FileEntryListenerAdapter {
    /**
     * Field files
     */
    private final JList files;
    /**
     * Field button
     */
    private final JButton button;

    /**
     * Constructor FileEntryListenerAdapter creates a new FileEntryListenerAdapter instance.
     *
     * @param files  of type JList
     * @param button of type JButton
     */
    public FileEntryListenerAdapter(final JList files, final JButton button) {
        this.files = files;
        this.button = button;
    }

    /**
     * {@inheritDoc}
     *
     * @see directorylister.controllers.FileEntryListenerAdapter#notifyFileEntryToSelect(FileEntry)
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
