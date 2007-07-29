package directorylister.controllers;

import directorylister.model.FileEntry;

/**
 * Defines interface for listening events about FileEntry.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:02:14
 */
public interface FileEntryListener {

    /**
     * Called when new FileEntry is selected in the tree.
     *
     * @param currentEntry current entry in tree.
     * @param newEntry     new entry.
     */
    void notifyCurrentFileEntryChanged(FileEntry currentEntry, FileEntry newEntry);

    /**
     * Called when entry is selected in tree.
     *
     * @param fileEntry selected entry.
     */
    void notifyFileEntryToSelect(final FileEntry fileEntry);
}
