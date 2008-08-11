package de.berlios.jalister.gui.actions.io.plugins;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressListener;
import de.berlios.jalister.parser.XMLParser;
import de.berlios.jalister.resources.ResourceHandler;
import de.berlios.jalister.search.Searcher;
import de.berlios.jalister.utils.SwingUtils;
import org.apache.commons.lang.SerializationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * User: bg
 * Date: 12.08.2008
 * Time: 0:07:39
 */
public class OpenXMLJaListerDatabasePlugin implements FileOpenSavePlugin {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(OpenXMLJaListerDatabasePlugin.class.getName());

    /**
     * Subclasses should perform load/save functionality in this method.
     *
     * @param selectedFile - file, selected by user.
     * @param listener     - callback for notifying progress.
     */
    public void handleFile(File selectedFile, ProgressListener listener) {
        try {
            final long startTime = System.currentTimeMillis();
            listener.notify(new Notification("Loading database..."));
            XMLParser parser = new XMLParser(selectedFile);
            final JaListerDatabase listerDatabase = parser.parse();
            listener.notify(new Notification("Building indexes..."));
            listerDatabase.attachService(new Searcher());
            JaListerDatabaseController.getInstance().setCurrentJaListerDatabase(listerDatabase);
            listener.notify(new Notification("Done."));
            final long loadTime = System.currentTimeMillis() - startTime;
            logger.info("Loaded in " + loadTime / (double) 1000 + " seconds");
        } catch (SAXException e) {
            SwingUtils.showError(e.getMessage());
            logger.fatal(e);
        } catch (FileNotFoundException e) {
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
    }

    /**
     * Get filechooser for action.
     *
     * @return - filechooser.
     */
    public JFileChooser getFileChooser() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(
                new FileNameExtensionFilter(ResourceHandler.getInstance().getMessage("FileType.XML"), "xml")
        );
        return fileChooser;
    }

    /**
     * Returns file extension used.
     *
     * @return file extension.
     */
    public String getFileExtension() {
        return "xml";
    }

    /**
     * Check, if dialog is for opening.
     *
     * @return - <code>true</code>, if dialog for opening,
     *         <code>false</code> otherwise.
     */
    public boolean isOpenDialog() {
        return true;
    }
}
