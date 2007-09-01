package directorylister.gui.actions.io.plugins;

import directorylister.controllers.JaListerDatabaseController;
import directorylister.gui.filters.JaListerDatabaseFileFilter;
import directorylister.model.JaListerDatabase;
import directorylister.notification.Notification;
import directorylister.notification.ProgressListener;
import directorylister.utils.SwingUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Class FileSaveTreePlugin ...
 *
 * @author schakal
 *         Created on 17.08.2007
 */
public class SaveJaListerDatabasePlugin implements FileOpenSavePlugin {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(OpenJaListerDatabasePlugin.class.getName());

    /**
     * {@inheritDoc} ** @see directorylister.gui.actions.FileOpenSavePlugin#handleFile(File, ProgressListener)
     */
    public void handleFile(final File selectedFile, final ProgressListener listener) {
        final JaListerDatabase listerDatabase = JaListerDatabaseController.getInstance().getCurrentDatabase();
        if (null != listerDatabase) {
            OutputStream outputStream = null;
            try {
                listener.notify(new Notification("Saving..."));
                outputStream = new GZIPOutputStream(new FileOutputStream(selectedFile));
                SerializationUtils.serialize(listerDatabase, outputStream);
                listener.notify(new Notification("Done."));
            }
            catch(FileNotFoundException e) {
                SwingUtils.showError(e.getMessage());
                logger.error(e.toString());
            }
            catch(IOException e) {
                SwingUtils.showError(e.getMessage());
                logger.error(e.toString());
            }
            finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    }
                    catch(IOException e) {
                        SwingUtils.showError(e.getMessage());
                        logger.error(e.toString());
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc} ** @see directorylister.gui.actions.FileOpenSavePlugin#getFileChooser()
     */
    public JFileChooser getFileChooser() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new JaListerDatabaseFileFilter());
        return fileChooser;
    }

    /**
     * {@inheritDoc}
     */
    public String getFileExtension() {
        return ".jalister";
    }

    /**
     * {@inheritDoc} ** @see directorylister.gui.actions.FileOpenSavePlugin#isOpenDialog()
     */
    public boolean isOpenDialog() {
        return false;
    }
}
