package directorylister.gui.components;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import directorylister.gui.actions.RunGarbageCollectorAction;
import directorylister.model.format.FileSizeFormatter;
import directorylister.resources.ResourceHandler;

/**
 * Component, which shows free memory indicator and allows to run garbage collector.
 *
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 10:23:56
 */
public class FreeMemoryIndicator extends JPanel {

    /**
     * Field progressBar
     */
    private JProgressBar progressBar;
    private static final int DEFAULT_UPDATE_PERIOD = 2000;

    private static final FileSizeFormatter FORMATTER = new FileSizeFormatter();
    private static final long serialVersionUID = -7468581539037689059L;

    /**
     * Constructs a new FreeMemoryIndicator.
     */
    public FreeMemoryIndicator() {

        final Timer timer = new Timer("Memory Indicator", true);
        timer.schedule(new UpdateProgressBarTask(), 0, DEFAULT_UPDATE_PERIOD);

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setStringPainted(true);
        progressBar.setIndeterminate(false);
        progressBar.setMaximum(500);

        add(progressBar);

        // TODO: Use icon.
        final JButton runGarbageCollector = new JButton();
        runGarbageCollector.setName("FreeMemoryIndicator.RunGC");
        runGarbageCollector.addActionListener(new RunGarbageCollectorAction());
        add(runGarbageCollector);

    }

    private class UpdateProgressBarAction implements Runnable {
        private final int usedMemoryPercents;
        private final String message;

        public UpdateProgressBarAction(final int usedMemoryPercents, final String message) {
            this.usedMemoryPercents = usedMemoryPercents;
            this.message = message;
        }

        /**
         * {@inheritDoc}
         */
        public void run() {
            progressBar.setValue(usedMemoryPercents);
            progressBar.setString(message);
            // TODO: Set more informative another tooltip.
            progressBar.setToolTipText(message);
        }
    }

    private class UpdateProgressBarTask extends TimerTask {
        @Override
        public void run() {
            final Runtime runtime = Runtime.getRuntime();
            final long totalMemory = runtime.totalMemory();
            final long freeMemory = runtime.freeMemory();
            final long usedMemory = totalMemory - freeMemory;

            final String usedMB = FORMATTER.format(usedMemory);
            final String totalMB = FORMATTER.format(totalMemory);
            final int usedMemoryPercents = 500 - (int) ((totalMemory - usedMemory) / (float) totalMemory * 500);
            final String message = ResourceHandler.getInstance().getFormattedMessage("FreeMemoryIndicator.MemoryUsed", usedMB, totalMB);

            EventQueue.invokeLater(new UpdateProgressBarAction(usedMemoryPercents, message));
        }
    }
}
