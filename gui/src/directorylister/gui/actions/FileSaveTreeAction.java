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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    public FileOpenSavePlugin getFileOpenSavePligin() {
        return new FileSaveTreePlugin();
    }

    private static class FileSaveTreePlugin implements FileOpenSavePlugin {

        public void handleFile(final File selectedFile, final ProgressListener listener) {
            final FileEntry fileEntry = FileEntryController.getInstance().getCurrentEntry();
            if (null != fileEntry) {
                OutputStream outputStream = null;
                try {

                    listener.notify(new Notification("Saving..."));
                    outputStream = new FileOutputStream(selectedFile);
                    SerializationUtils.serialize(fileEntry, outputStream);
                    listener.notify(new Notification("Done."));
                } catch (FileNotFoundException e) {
                    logger.error(e.toString());
                }
                finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            logger.error(e.toString());
                        }
                    }
                }
            }
        }

        public JFileChooser getFileChooser() {
            return new JFileChooser();
        }

        public boolean isOpenDialog() {
            return false;
        }
    }
}