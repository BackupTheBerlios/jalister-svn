package directorylister.controllers;

import directorylister.model.FileEntry;

/**
 * Default implementation of the <code>FileEntryListener</code>.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:06:07
 */
public class FileEntryListenerAdapter implements FileEntryListener {
    /**
     * {@inheritDoc}
     */
    public void notifyCurrentFileEntryChanged(FileEntry currentEntry, FileEntry newEntry) {
    }

    /**
     * {@inheritDoc}
     */
    public void notifyFileEntryToSelect(final FileEntry fileEntry) {
    }
}
