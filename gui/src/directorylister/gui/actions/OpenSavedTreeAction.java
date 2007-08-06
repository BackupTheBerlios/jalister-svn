package directorylister.gui.actions;

import directorylister.controllers.FileEntryController;
import directorylister.gui.MainWindow;
import directorylister.model.FileEntry;
import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * User: bg
 * Date: 15.07.2007
 * Time: 19:10:21
 */
public final class OpenSavedTreeAction implements ActionListener {

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(OpenSavedTreeAction.class);
    /**
     * Field mainWindow
     */
    private final MainWindow mainWindow;

    /**
     * Constructor OpenSavedTreeAction creates a new OpenSavedTreeAction instance.
     *
     * @param mainWindow of type MainWindow
     */
    public OpenSavedTreeAction(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.showOpenDialog(mainWindow);

        File selectedFile = fileChooser.getSelectedFile();
        if (null != selectedFile) {
            try {
                InputStream inputStream = new FileInputStream(selectedFile);
                FileEntry fileEntry = (FileEntry) SerializationUtils.deserialize(inputStream);

                FileEntryController.getInstance().setCurrentFileEntry(fileEntry);

            } catch(FileNotFoundException e) {
                logger.error(e.toString());
            }
            catch(SerializationException e) {
                logger.error(e.toString());
            }
        }
    }
}
