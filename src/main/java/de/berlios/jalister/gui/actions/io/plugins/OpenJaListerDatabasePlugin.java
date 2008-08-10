package de.berlios.jalister.gui.actions.io.plugins;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.gui.filters.JaListerDatabaseFileFilter;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressListener;
import de.berlios.jalister.search.Searcher;
import de.berlios.jalister.utils.SwingUtils;
import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Plugin for opening saved databases.
 *
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 13:56:40
 */
public class OpenJaListerDatabasePlugin implements FileOpenSavePlugin {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(OpenJaListerDatabasePlugin.class.getName());

    /**
     * @see FileOpenSavePlugin#handleFile(File,ProgressListener)
     */
    public void handleFile(final File selectedFile, final ProgressListener listener) {
        InputStream inputStream = null;
        try {
            // TODO: Localization.
            inputStream = new GZIPInputStream(new FileInputStream(selectedFile));
            final long startTime = System.currentTimeMillis();
            listener.notify(new Notification("Loading database..."));
            final JaListerDatabase listerDatabase = (JaListerDatabase) SerializationUtils.deserialize(inputStream);
            listener.notify(new Notification("Building indexes..."));
            listerDatabase.attachService(new Searcher());
            JaListerDatabaseController.getInstance().setCurrentJaListerDatabase(listerDatabase);
            listener.notify(new Notification("Done."));
            final long loadTime = System.currentTimeMillis() - startTime;

            logger.info("Loaded in " + loadTime / (double) 1000 + " seconds");

        }
        catch (FileNotFoundException e) {
            SwingUtils.showError(e.getMessage());
            logger.error(e.toString());
        }
        catch (SerializationException e) {
            SwingUtils.showError(e.getMessage());
            logger.error(e.toString());
        }
        catch (IOException e) {
            SwingUtils.showError(e.getMessage());
            logger.error(e.toString());
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    logger.error(e.toString());
                }
            }
        }
    }


    /**
     * @see FileOpenSavePlugin#getFileChooser()
     */
    public JFileChooser getFileChooser() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new JaListerDatabaseFileFilter());
        return fileChooser;
    }

    /**
     * @see FileOpenSavePlugin#getFileExtension()
     */
    public String getFileExtension() {
        return ".jalister";
    }

    /**
     * @see FileOpenSavePlugin#isOpenDialog()
     */
    public boolean isOpenDialog() {
        return true;
    }
}
