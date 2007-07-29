package directorylister.startup;

import directorylister.resources.ResourceHandler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for managing startup process.
 */
public class StartupProgressNotifier {

    private final List<StartupListener> listeners;

    /**
     * Constructs a new StartupProgressNotifier.
     */
    public StartupProgressNotifier() {
        listeners = Collections.synchronizedList(new LinkedList<StartupListener>());
    }

    /**
     * Notifies all listeners about new startup status.
     *
     * @param message - notification message to broadcast.
     */
    public void notifyListeners(final String message) {

        final String localizedMessage = ResourceHandler.getInstance().getMessage(message);

        for (final StartupListener listener : listeners) {
            listener.notify(localizedMessage);
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

    /**
     * Add new startup listener.
     *
     * @param startupListener Startup Progress Listener to add.
     * @return <code>true</code>, if startupListener was added, <code>false</code> otherwise.
     */
    public boolean addListener(final StartupListener startupListener) {
        return !listeners.contains(startupListener) && listeners.add(startupListener);
    }
}
