package de.berlios.jalister.gui.actions.io;

import de.berlios.jalister.gui.actions.io.plugins.FileOpenSavePlugin;
import de.berlios.jalister.gui.actions.io.plugins.SaveJaListerDatabasePlugin;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 30.07.2007
 * Time: 23:28:35
 */
public final class FileSaveTreeAction extends AbstractFileOpenSaveActionWithProgressBar {

    /**
     * Constructor FileSaveTreeAction creates a new FileSaveTreeAction instance.
     *
     * @param frame - main frame.
     */
    public FileSaveTreeAction(final JFrame frame) {
        super(frame);
    }

    /**
     * {@inheritDoc} ** @see directorylister.gui.actions.AbstractFileOpenSaveActionWithProgressBar#getFileOpenSavePligin()
     */
    @Override
    public FileOpenSavePlugin getFileOpenSavePligin() {
        return new SaveJaListerDatabasePlugin();
    }

}