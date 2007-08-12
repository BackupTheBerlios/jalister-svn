package directorylister.gui.actions;

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
    private final JFrame frame;

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
        final JFileChooser directoryChooser = openSavePlugin.getFileChooser();

        final int returnVal = showDialog(openSavePlugin.isOpenDialog(), directoryChooser);

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
                                openSavePlugin.handleFile(selectedFile, frame);
                            } finally {
                                frame.setVisible(false);
                            }
                        }
                    });
        }
    }

    private int showDialog(final boolean openDialog, final JFileChooser directoryChooser) {
        final int returnVal;
        if (openDialog) {
            returnVal = directoryChooser.showOpenDialog(frame);
        } else {
            returnVal = directoryChooser.showSaveDialog(frame);
        }
        return returnVal;
    }

    public abstract FileOpenSavePlugin getFileOpenSavePligin();
}