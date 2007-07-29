package directorylister.gui.actions;

import directorylister.controllers.FileEntryController;
import directorylister.gui.MainWindow;
import directorylister.model.FileEntry;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 15.07.2007
 * Time: 18:11:24
 * To change this template use File | Settings | File Templates.
 */

public class FileSaveXMLAction implements ActionListener {
    private static final Log logger = LogFactory.getLog(FileSaveXMLAction.class);

    private MainWindow mainWindow;


    public FileSaveXMLAction(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);

        File selectedFile = fileChooser.getSelectedFile();

        FileEntry fileEntry = FileEntryController.getInstance().getCurrentEntry();

        if (null != fileEntry) {
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(selectedFile);
                SerializationUtils.serialize(fileEntry, outputStream);
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
}
