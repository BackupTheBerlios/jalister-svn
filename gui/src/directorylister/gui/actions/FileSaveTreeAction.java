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
 * Date: 30.07.2007
 * Time: 23:28:35
 */
public final class FileSaveTreeAction implements ActionListener {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(FileSaveXMLAction.class);

    /**
     * Field mainWindow
     */
    private final MainWindow mainWindow;


    /**
     * Constructor FileSaveTreeAction creates a new FileSaveTreeAction instance.
     *
     * @param mainWindow of type MainWindow
     */
    public FileSaveTreeAction(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);

        File selectedFile = fileChooser.getSelectedFile();
        if (null == selectedFile) {
            return;
        }

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