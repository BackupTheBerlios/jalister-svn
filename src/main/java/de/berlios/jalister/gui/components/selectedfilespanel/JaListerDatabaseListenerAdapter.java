package de.berlios.jalister.gui.components.selectedfilespanel;

import de.berlios.jalister.model.FileEntry;

import javax.swing.*;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:22:57
 */
final class JaListerDatabaseListenerAdapter extends de.berlios.jalister.controllers.JaListerDatabaseListenerAdapter {
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
     * @see de.berlios.jalister.controllers.JaListerDatabaseListenerAdapter#notifyFileEntryRightClicked(FileEntry)
     */
    @Override
    public void notifyFileEntryRightClicked(final FileEntry fileEntry) {
        final DefaultListModel listModel = (DefaultListModel) files.getModel();
        if (listModel.contains(fileEntry)) {
            listModel.removeElement(fileEntry);
        } else {
            listModel.addElement(fileEntry);
        }
    }
}
