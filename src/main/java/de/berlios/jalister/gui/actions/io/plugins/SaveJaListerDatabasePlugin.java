package de.berlios.jalister.gui.actions.io.plugins;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.gui.filters.JaListerDatabaseFileFilter;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressListener;
import de.berlios.jalister.utils.SwingUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.io.*;
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
            catch (FileNotFoundException e) {
                SwingUtils.showError(e.getMessage());
                logger.error(e.toString());
            }
            catch (IOException e) {
                SwingUtils.showError(e.getMessage());
                logger.error(e.toString());
            }
            finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    }
                    catch (IOException e) {
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
