package de.berlios.jalister.gui.actions.io.plugins;

import de.berlios.jalister.notification.ProgressListener;

import javax.swing.*;
import java.io.File;

/**
 * Interface for file isOpenDialog/save actions.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 11.08.2007 3:45:36
 */
public interface FileOpenSavePlugin {

    /**
     * Subclasses should perform load/save functionality in this method.
     *
     * @param selectedFile - file, selected by user.
     * @param listener     - callback for notifying progress.
     */
    void handleFile(File selectedFile, ProgressListener listener);

    /**
     * Get filechooser for action.
     *
     * @return - filechooser.
     */
    JFileChooser getFileChooser();

    /**
     * Returns file extension used.
     *
     * @return file extension.
     */
    String getFileExtension();

    /**
     * Check, if dialog is for opening.
     *
     * @return - <code>true</code>, if dialog for opening,
     *         <code>false</code> otherwise.
     */
    boolean isOpenDialog();
}
