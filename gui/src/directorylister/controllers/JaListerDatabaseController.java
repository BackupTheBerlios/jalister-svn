package directorylister.controllers;

import directorylister.model.FileEntry;
import directorylister.model.JaListerDatabase;

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
public class JaListerDatabaseController {

    /**
     * Field LISTENERS
     */
    private final List<JaListerDatabaseListener> LISTENERS = Collections.synchronizedList(new LinkedList<JaListerDatabaseListener>());

    /**
     * Field INSTANCE
     */
    private static final JaListerDatabaseController INSTANCE = new JaListerDatabaseController();
    /**
     * Field currentDatabase
     */
    private JaListerDatabase currentDatabase = null;
    /**
     * Field selectedFileEntry
     */
    private FileEntry selectedFileEntry = null;

    /**
     * Getter for property 'instance'.
     *
     * @return Value for property 'instance'.
     */
    public static JaListerDatabaseController getInstance() {
        return INSTANCE;
    }

    /**
     * Adds the listener to the controller. This will allow listener
     * receive further notifications about file entry.
     *
     * @param listener listener to add.
     */
    public void addListener(final JaListerDatabaseListener listener) {
        LISTENERS.add(listener);

        if (null != currentDatabase) {
            listener.notifyJaListerDatabaseChanged(currentDatabase, currentDatabase);
        }
    }

    /**
     * Setter for property 'currentFileEntry'.
     *
     * @param newEntry Value to set for property 'currentFileEntry'.
     */
    public void setCurrentJaListerDatabase(final JaListerDatabase newEntry) {

        for (final JaListerDatabaseListener listerDatabaseListener : LISTENERS) {
            listerDatabaseListener.notifyJaListerDatabaseChanged(currentDatabase, newEntry);
        }
        currentDatabase = newEntry;
    }

    /**
     * Getter for property 'currentDatabase'.
     *
     * @return Value for property 'currentDatabase'.
     */
    public JaListerDatabase getCurrentDatabase() {
        return currentDatabase;
    }

    /**
     * Select new file entry.
     *
     * @param fileEntry to select.
     */
    public void fileEntryRightClicked(final FileEntry fileEntry) {
        for (final JaListerDatabaseListener listerDatabaseListener : LISTENERS) {
            listerDatabaseListener.notifyFileEntryRightClicked(fileEntry);
        }

        selectedFileEntry = fileEntry;
    }

    public void fileEntrySelected(final FileEntry fileEntry) {
        for (final JaListerDatabaseListener listerDatabaseListener : LISTENERS) {
            listerDatabaseListener.notifyFileEntrySelected(fileEntry);
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
