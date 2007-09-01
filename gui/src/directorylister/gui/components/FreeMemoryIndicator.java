package directorylister.gui.components;

import directorylister.gui.actions.RunGarbageCollectorAction;
import directorylister.resources.ResourceHandler;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.TimerTask;

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
    private JButton runGarbageCollector;
    private static final int BYTES_IN_MEGABYTE = 1048576;
    private static final int DEFAULT_UPDATE_PERIOD = 2000;

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
        runGarbageCollector = new JButton();
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
        public void run() {
            final Runtime runtime = Runtime.getRuntime();
            final long totalMemory = runtime.totalMemory();
            final long freeMemory = runtime.freeMemory();
            final long usedMemory = totalMemory - freeMemory;
            final double usedMB = (double) usedMemory / BYTES_IN_MEGABYTE;
            final double totalMB = (double) totalMemory / BYTES_IN_MEGABYTE;
            final int usedMemoryPercents = 500 - (int) ((totalMemory - usedMemory) / (float) totalMemory * 500);
            final String message = ResourceHandler.getInstance().getFormattedMessage("FreeMemoryIndicator.MemoryUsed", usedMB, totalMB);

            SwingUtilities.invokeLater(new UpdateProgressBarAction(usedMemoryPercents, message));
        }
    }
}
