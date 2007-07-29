package directorylister.controllers;

import directorylister.model.FileEntry;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for FileEntry entity.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:01:20
 */
public class FileEntryController {

    private final List<FileEntryListener> LISTENERS = Collections.synchronizedList(new LinkedList<FileEntryListener>());

    private static final FileEntryController INSTANCE = new FileEntryController();
    private FileEntry currentEntry;
    private FileEntry selectedFileEntry;

    /**
     * Getter for property 'instance'.
     *
     * @return Value for property 'instance'.
     */
    public static FileEntryController getInstance() {
        return INSTANCE;
    }

    /**
     * Adds the listener to the controller. This will allow listener
     * receive further notifications about file entry.
     *
     * @param listener listener to add.
     */
    public void addListener(final FileEntryListener listener) {
        LISTENERS.add(listener);
    }

    /**
     * Setter for property 'currentFileEntry'.
     *
     * @param newEntry Value to set for property 'currentFileEntry'.
     */
    public void setCurrentFileEntry(final FileEntry newEntry) {

        for (FileEntryListener fileEntryListener : LISTENERS) {
            fileEntryListener.notifyCurrentFileEntryChanged(currentEntry, newEntry);
        }
        currentEntry = newEntry;
    }

    /**
     * Getter for property 'currentEntry'.
     *
     * @return Value for property 'currentEntry'.
     */
    public FileEntry getCurrentEntry() {
        return currentEntry;
    }

    /**
     * Select new file entry.
     *
     * @param fileEntry
     */
    public void selectFileEntry(final FileEntry fileEntry) {
        for (FileEntryListener fileEntryListener : LISTENERS) {
            fileEntryListener.notifyFileEntryToSelect(fileEntry);
        }

        selectedFileEntry = fileEntry;
    }


    /**
     * Getter for property 'selectedFileEntry'.
     *
     * @return Value for property 'selectedFileEntry'.
     */
    public FileEntry getSelectedFileEntry() {
        return selectedFileEntry;
    }
}
