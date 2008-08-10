package de.berlios.jalister.gui.actions.io;

import de.berlios.jalister.gui.actions.io.plugins.FileOpenSavePlugin;
import de.berlios.jalister.gui.actions.io.plugins.LoadDirectoryPlugin;

import javax.swing.*;

/**
 * Action for loading directories from filesystem.
 *
 * @author bg, Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.1
 * @since 15.07.2007 17:02:31
 */
public final class ChooseDirectoryAction extends AbstractFileOpenSaveActionWithProgressBar {
    /**
     * Field plugin
     */
    private final LoadDirectoryPlugin plugin;

    /**
     * Constructs a new ChooseDirectoryAction.
     *
     * @param frame - main frame.
     */
    public ChooseDirectoryAction(final JFrame frame) {
        super(frame);
        plugin = new LoadDirectoryPlugin();
    }


    /**
     * {@inheritDoc} ** @see directorylister.gui.actions.AbstractFileOpenSaveActionWithProgressBar#getFileOpenSavePligin()
     */
    @Override
    public FileOpenSavePlugin getFileOpenSavePligin() {
        return plugin;
    }
}
