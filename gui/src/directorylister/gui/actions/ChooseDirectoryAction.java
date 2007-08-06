package directorylister.gui.actions;

import directorylister.controllers.FileEntryController;
import directorylister.gui.components.DirectoryChooser;
import directorylister.gui.components.ProgressBarFrame;
import directorylister.model.FileEntry;
import directorylister.parser.FileSystemParser;
import directorylister.startup.ProgressListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * User: bg
 * Date: 15.07.2007
 * Time: 17:02:31
 */
public final class ChooseDirectoryAction implements ActionListener {
    /**
     * Field EXECUTOR
     */
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    /**
     * Field logger
     */
    private final Log logger = LogFactory.getLog(ChooseDirectoryAction.class);

    /**
     * Constructs a new ChooseDirectoryAction.
     */
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

            EXECUTOR.execute(
                    new Runnable() {
                        /**
                         * {@inheritDoc}
                         */
                        public void run() {
                            final ProgressBarFrame frame = new ProgressBarFrame();
                            frame.setVisible(true);
                            frame.setAlwaysOnTop(true);
                            try {
                                FileEntry fileEntry = null;
                                try {
                                    fileEntry = parseFile(selectedFile, frame);
                                } catch(IOException e) {
                                    logger.error(e);
                                }
                                if (null != fileEntry) {
                                    FileEntryController.getInstance().setCurrentFileEntry(fileEntry);
                                }

                            } finally {
                                frame.setVisible(false);
                            }
                        }
                    });


        }
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
