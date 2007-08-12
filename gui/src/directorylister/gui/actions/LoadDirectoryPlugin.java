package directorylister.gui.actions;

import directorylister.controllers.FileEntryController;
import directorylister.gui.components.DirectoryChooser;
import directorylister.model.FileEntry;
import directorylister.notification.ProgressListener;
import directorylister.parser.FileSystemParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 11.08.2007 3:13:18
 */
public class LoadDirectoryPlugin implements FileOpenSavePlugin {
    private static final Log logger = LogFactory.getLog(LoadDirectoryPlugin.class);

    public void handleFile(final File selectedFile, final ProgressListener frame) {
        FileEntry fileEntry = null;
        try {
            fileEntry = parseFile(selectedFile, frame);
        } catch (IOException e) {
            logger.error(e);
        }
        if (null != fileEntry) {
            FileEntryController.getInstance().setCurrentFileEntry(fileEntry);
        }
    }


    public JFileChooser getFileChooser() {
        return new DirectoryChooser();
    }

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
    private FileEntry parseFile(final File selectedFile, final ProgressListener frame) throws IOException {
        final FileSystemParser systemParser = new FileSystemParser(selectedFile);
        systemParser.addListener(frame);
        return systemParser.parse();
    }


}
