package directorylister.notification;

import directorylister.resources.ResourceHandler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for managing notification process.
 */
public class ProgressNotifier {

    /**
     * Field listeners
     */
    private final List<ProgressListener> listeners;

    /**
     * Constructs a new StartupProgressNotifier.
     */
    public ProgressNotifier() {
        listeners = Collections.synchronizedList(new LinkedList<ProgressListener>());
    }

    /**
     * Notifies all listeners about new notification status.
     *
     * @param message - notification message to broadcast.
     */
    public void notifyListeners(final String message, final Object... values) {

        final String localizedMessage = ResourceHandler.getInstance().getFormattedMessage(message, values);

        final Notification notification = new Notification(localizedMessage);
        for (final ProgressListener listener : listeners) {
            listener.notify(notification);
        }
    }

    /**
     * Add new notification listener.
     *
     * @param startupListener Startup Progress Listener to add.
     * @return <code>true</code>, if startupListener was added, <code>false</code> otherwise.
     */
    public boolean addListener(final ProgressListener startupListener) {
        return !listeners.contains(startupListener) && listeners.add(startupListener);
    }
}
