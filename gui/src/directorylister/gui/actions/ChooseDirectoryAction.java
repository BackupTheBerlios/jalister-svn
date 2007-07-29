package directorylister.gui.actions;

import directorylister.controllers.FileEntryController;
import directorylister.gui.components.DirectoryChooser;
import directorylister.model.FileEntry;
import directorylister.parser.FileSystemParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 15.07.2007
 * Time: 17:02:31
 * To change this template use File | Settings | File Templates.
 */
public class ChooseDirectoryAction implements ActionListener {

    private Log logger = LogFactory.getLog(ChooseDirectoryAction.class);

    public ChooseDirectoryAction() {

    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent actionEvent) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();

        int returnVal = directoryChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File selectedFile = directoryChooser.getSelectedFile();
            logger.info("You've chosen: " + selectedFile.getAbsolutePath());

            FileEntry fileEntry = null;
            try {
                fileEntry = parseFile(selectedFile);
            } catch (IOException e) {
                logger.error(e);
            }
            if (null != fileEntry) {
                FileEntryController.getInstance().setCurrentFileEntry(fileEntry);
            }
        }
    }

    private FileEntry parseFile(File selectedFile) throws IOException {
        return new FileSystemParser(selectedFile).parse();
    }


}
