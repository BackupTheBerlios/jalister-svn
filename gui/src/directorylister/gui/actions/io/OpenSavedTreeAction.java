package directorylister.gui.actions.io;

import directorylister.gui.actions.io.plugins.FileOpenSavePlugin;
import directorylister.gui.actions.io.plugins.OpenJaListerDatabasePlugin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFrame;

/**
 * User: bg
 * Date: 15.07.2007
 * Time: 19:10:21
 */
public final class OpenSavedTreeAction extends AbstractFileOpenSaveActionWithProgressBar {

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(OpenSavedTreeAction.class);

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
    public FileOpenSavePlugin getFileOpenSavePligin() {
        return new OpenJaListerDatabasePlugin();
    }

}
