package directorylister.controllers;

import directorylister.model.FileEntry;
import directorylister.model.JaListerDatabase;

/**
 * Default implementation of the <code>JaListerDatabaseListener</code>.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:06:07
 */
public class JaListerDatabaseListenerAdapter implements JaListerDatabaseListener {
    /**
     * {@inheritDoc}
     *
     * @see JaListerDatabaseListener#notifyJaListerDatabaseChanged(JaListerDatabase,JaListerDatabase)
     */
    public void notifyJaListerDatabaseChanged(final JaListerDatabase currentDatabase, final JaListerDatabase newDatabase) {
    }

    /**
     * {@inheritDoc}
     *
     * @see JaListerDatabaseListener#notifyFileEntryRightClicked(FileEntry)
     */
    public void notifyFileEntryRightClicked(final FileEntry fileEntry) {
    }

    /**
     * {@inheritDoc}
     */
    public void notifyFileEntrySelected(final FileEntry fileEntry) {
    }
}
