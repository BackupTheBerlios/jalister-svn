package de.berlios.jalister.gui.actions.io;

import de.berlios.jalister.XMLSerializer;
import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.resources.ResourceHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User: bg
 * Date: 15.07.2007
 * Time: 18:11:24
 */

public final class FileSaveXMLAction implements ActionListener {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(FileSaveXMLAction.class);


    /**
     * Constructor FileSaveXMLAction creates a new FileSaveXMLAction instance.
     */
    public FileSaveXMLAction() {
        // TODO: Think if this is necessary
    }

    /**
     * {@inheritDoc}
     * Action handler
     */
    public void actionPerformed(final ActionEvent actionEvent) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(
                new FileNameExtensionFilter(ResourceHandler.getInstance().getMessage("FileType.XML"), "xml")
        );
        fileChooser.showSaveDialog(null);

        final File selectedFile = fileChooser.getSelectedFile();
        if (null == selectedFile) {
            return;
        }

        final JaListerDatabase listerDatabase = JaListerDatabaseController.getInstance().getCurrentDatabase();

        if (null != listerDatabase) {
            FileEntry rootEntry = listerDatabase.getRootEntry();
            if (null != rootEntry) {
                try {
                    FileOutputStream outputStream = new FileOutputStream(selectedFile);
                    XMLSerializer.serialize(outputStream, rootEntry);
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    logger.debug(e);
                } catch (IOException e) {
                    logger.debug(e);
                }
            }
        }
    }
}
