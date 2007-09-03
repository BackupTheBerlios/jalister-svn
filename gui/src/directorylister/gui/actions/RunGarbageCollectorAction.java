package directorylister.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Run garbage collector.
 *
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 10:58:11
 */
public class RunGarbageCollectorAction implements ActionListener {

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent e) {
        final Runtime runtime = Runtime.getRuntime();
        runtime.runFinalization();
        runtime.gc();
    }
}
