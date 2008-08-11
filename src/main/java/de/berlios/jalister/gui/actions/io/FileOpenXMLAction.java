package de.berlios.jalister.gui.actions.io;

import de.berlios.jalister.gui.actions.io.plugins.FileOpenSavePlugin;
import de.berlios.jalister.gui.actions.io.plugins.OpenXMLJaListerDatabasePlugin;

import javax.swing.*;

/**
 * User: bg
 * Date: 12.08.2008
 * Time: 0:02:19
 */
public class FileOpenXMLAction extends AbstractFileOpenSaveActionWithProgressBar {


    /**
     * Constructor AbstractFileOpenSaveActionWithProgressBar creates a new AbstractFileOpenSaveActionWithProgressBar instance.
     *
     * @param frame of type JFrame
     */
    public FileOpenXMLAction(final JFrame frame) {
        super(frame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileOpenSavePlugin getFileOpenSavePligin() {
        return new OpenXMLJaListerDatabasePlugin();
    }


}
