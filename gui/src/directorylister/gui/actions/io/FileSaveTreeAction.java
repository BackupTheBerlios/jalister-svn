package directorylister.gui.actions.io;

import directorylister.gui.actions.io.plugins.FileOpenSavePlugin;
import directorylister.gui.actions.io.plugins.SaveJaListerDatabasePlugin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFrame;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 30.07.2007
 * Time: 23:28:35
 */
public final class FileSaveTreeAction extends AbstractFileOpenSaveActionWithProgressBar {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(FileSaveXMLAction.class);

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