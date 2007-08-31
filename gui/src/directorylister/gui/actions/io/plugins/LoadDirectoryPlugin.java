package directorylister.gui.actions.io.plugins;

import directorylister.controllers.JaListerDatabaseController;
import directorylister.gui.components.DirectoryChooser;
import directorylister.model.JaListerDatabase;
import directorylister.notification.ProgressListener;
import directorylister.parser.FileSystemParser;
import directorylister.utils.SwingUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 11.08.2007 3:13:18
 */
public class LoadDirectoryPlugin implements FileOpenSavePlugin {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(LoadDirectoryPlugin.class);

    /**
     * {@inheritDoc} ** @see directorylister.gui.actions.FileOpenSavePlugin#handleFile(File, ProgressListener)
     */
    public void handleFile(final File selectedFile, final ProgressListener frame) {
        JaListerDatabase jaListerDatabase = null;
        try {
            jaListerDatabase = parseFile(selectedFile, frame);
        } catch(IOException e) {
            SwingUtils.showError(e.getMessage());
            logger.error(e);
        }
        if (null != jaListerDatabase) {
            JaListerDatabaseController.getInstance().setCurrentJaListerDatabase(jaListerDatabase);
        }
    }


    /**
     * {@inheritDoc} ** @see directorylister.gui.actions.FileOpenSavePlugin#getFileChooser()
     */
    public JFileChooser getFileChooser() {
        return new DirectoryChooser();
    }

    /**
     * {@inheritDoc}
     */
    public String getFileExtension() {
        return null;
    }

    /**
     * {@inheritDoc} ** @see directorylister.gui.actions.FileOpenSavePlugin#isOpenDialog()
     */
    public boolean isOpenDialog() {
        return true;
    }


    /**
     * Method parseFile ...
     *
     * @param selectedFile of type File
     * @param frame        of type ProgressListener
     * @return FileEntry
     * @throws IOException when
     */
    private JaListerDatabase parseFile(final File selectedFile, final ProgressListener frame) throws IOException {
        final FileSystemParser systemParser = new FileSystemParser(selectedFile);
        systemParser.addListener(frame);
        return systemParser.parse();
    }


}
