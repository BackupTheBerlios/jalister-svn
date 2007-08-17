package directorylister.controllers;

import directorylister.model.FileEntry;
import directorylister.model.JaListerDatabase;

/**
 * Defines interface for listening events about FileEntry.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:02:14
 */
public interface JaListerDatabaseListener {

    /**
     * Called when new FileEntry is selected in the tree.
     *
     * @param currentDatabase current entry in tree.
     * @param newDatabase     new entry.
     */
    void notifyJaListerDatabaseChanged(final JaListerDatabase currentDatabase, final JaListerDatabase newDatabase);

    /**
     * Called when entry is selected in tree.
     *
     * @param fileEntry selected entry.
     */
    void notifyFileEntryToSelect(final FileEntry fileEntry);
}