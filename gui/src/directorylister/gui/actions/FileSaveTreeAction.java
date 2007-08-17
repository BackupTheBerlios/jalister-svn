package directorylister.gui.actions;

import directorylister.controllers.FileEntryController;
import directorylister.model.FileEntry;
import directorylister.notification.Notification;
import directorylister.notification.ProgressListener;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.*;

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
        return new FileSaveTreePlugin();
    }

    /**
     * Class FileSaveTreePlugin ...
     *
     * @author schakal
     *         Created on 17.08.2007
     */
    private static class FileSaveTreePlugin implements FileOpenSavePlugin {

        /**
         * {@inheritDoc} ** @see directorylister.gui.actions.FileOpenSavePlugin#handleFile(File, ProgressListener)
         */
        public void handleFile(final File selectedFile, final ProgressListener listener) {
            final FileEntry fileEntry = FileEntryController.getInstance().getCurrentEntry();
            if (null != fileEntry) {
                OutputStream outputStream = null;
                try {

                    listener.notify(new Notification("Saving..."));
                    outputStream = new FileOutputStream(selectedFile);
                    SerializationUtils.serialize(fileEntry, outputStream);
                    listener.notify(new Notification("Done."));
                } catch(FileNotFoundException e) {
                    logger.error(e.toString());
                }
                finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch(IOException e) {
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
            return new JFileChooser();
        }

        /**
         * {@inheritDoc} ** @see directorylister.gui.actions.FileOpenSavePlugin#isOpenDialog()
         */
        public boolean isOpenDialog() {
            return false;
        }
    }
}