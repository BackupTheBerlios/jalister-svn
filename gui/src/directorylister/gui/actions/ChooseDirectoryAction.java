package directorylister.gui.actions;

import javax.swing.JFrame;

/**
 * Action for loading directories from filesystem.
 *
 * @author bg, Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.1
 * @since 15.07.2007 17:02:31
 */
public final class ChooseDirectoryAction extends AbstractFileOpenSaveActionWithProgressBar {
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


    public FileOpenSavePlugin getFileOpenSavePligin() {
        return plugin;
    }
}
