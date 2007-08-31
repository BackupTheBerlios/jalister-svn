package directorylister.gui.actions.io;

import directorylister.gui.actions.io.plugins.FileOpenSavePlugin;
import directorylister.gui.components.ProgressBarFrame;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Generic action for performing loading/save actions with user callback.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 11.08.2007 3:44:44
 */
public abstract class AbstractFileOpenSaveActionWithProgressBar implements ActionListener {
    /**
     * Field EXECUTOR
     */
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    /**
     * Field frame
     */
    private final JFrame frame;

    /**
     * Constructor AbstractFileOpenSaveActionWithProgressBar creates a new AbstractFileOpenSaveActionWithProgressBar instance.
     *
     * @param frame of type JFrame
     */
    public AbstractFileOpenSaveActionWithProgressBar(final JFrame frame) {
        this.frame = frame;
    }

    /**
     * Field logger
     */
    private final Log logger = LogFactory.getLog(ChooseDirectoryAction.class);

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent actionEvent) {
        final FileOpenSavePlugin openSavePlugin = getFileOpenSavePligin();
        final JFileChooser fileChooser = openSavePlugin.getFileChooser();

        final int returnVal = showDialog(openSavePlugin.isOpenDialog(), fileChooser);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File selectedFile = getSelectedFile(fileChooser, openSavePlugin);
            logger.info("You've chosen: " + selectedFile.getAbsolutePath());
            EXECUTOR.execute(
                    new Runnable() {
                        /**
                         * {@inheritDoc}
                         */
                        public void run() {
                            frame.setEnabled(false);
                            final ProgressBarFrame progressBar = new ProgressBarFrame();
                            progressBar.setVisible(true);

                            progressBar.setAlwaysOnTop(true);
                            try {
                                openSavePlugin.handleFile(selectedFile, progressBar);
                            } finally {
                                progressBar.setVisible(false);
                                frame.setEnabled(true);
                            }
                        }
                    });
        }
    }

    /**
     * Method getSelectedFile ...
     *
     * @param fileChooser    of type JFileChooser
     * @param openSavePlugin of type FileOpenSavePlugin
     * @return File selected file.
     */
    private static File getSelectedFile(final JFileChooser fileChooser, final FileOpenSavePlugin openSavePlugin) {
        File selectedFile = fileChooser.getSelectedFile();
        if (!fileChooser.getFileFilter().accept(selectedFile) && !openSavePlugin.isOpenDialog()) {
            final String fileName = selectedFile.getAbsolutePath() + openSavePlugin.getFileExtension();
            selectedFile = new File(fileName);
        }
        return selectedFile;
    }

    /**
     * Method showDialog ...
     *
     * @param openDialog       of type boolean
     * @param directoryChooser of type JFileChooser
     * @return int
     */
    private int showDialog(final boolean openDialog, final JFileChooser directoryChooser) {
        final int returnVal;
        if (openDialog) {
            returnVal = directoryChooser.showOpenDialog(frame);
        }
        else {
            returnVal = directoryChooser.showSaveDialog(frame);
        }
        return returnVal;
    }

    /**
     * Getter for property 'fileOpenSavePligin'.
     *
     * @return Value for property 'fileOpenSavePligin'.
     */
    public abstract FileOpenSavePlugin getFileOpenSavePligin();
}
