package de.berlios.jalister.gui.actions.io;

import de.berlios.jalister.gui.actions.io.plugins.FileOpenSavePlugin;
import de.berlios.jalister.gui.actions.io.plugins.OpenJaListerDatabasePlugin;

import javax.swing.*;

/**
 * User: bg
 * Date: 15.07.2007
 * Time: 19:10:21
 */
public final class OpenSavedTreeAction extends AbstractFileOpenSaveActionWithProgressBar {

    /**
     * Constructor AbstractFileOpenSaveActionWithProgressBar creates a new AbstractFileOpenSaveActionWithProgressBar instance.
     *
     * @param frame of type JFrame
     */
    public OpenSavedTreeAction(final JFrame frame) {
        super(frame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileOpenSavePlugin getFileOpenSavePligin() {
        return new OpenJaListerDatabasePlugin();
    }

}
