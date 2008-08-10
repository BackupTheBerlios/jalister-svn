package de.berlios.jalister.gui.actions.io.plugins;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.gui.components.DirectoryChooser;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.notification.ProgressListener;
import de.berlios.jalister.parser.FileSystemParser;
import de.berlios.jalister.utils.SwingUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
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
        }
        catch (IOException e) {
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
    private static JaListerDatabase parseFile(final File selectedFile, final ProgressListener frame) throws IOException {
        final FileSystemParser systemParser = new FileSystemParser(selectedFile);
        systemParser.addListener(frame);
        return systemParser.parse();
    }


}
