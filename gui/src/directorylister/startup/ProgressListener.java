package directorylister.startup;

/**
 * Interface for progress listener.
 *
 * @author Oleg Atamanenko.
 * @version 1.0
 * @since 22.07.2007 3:13:53
 */
public interface ProgressListener {
    /**
     * Called when new notification received.
     *
     * @param message - notification message.
     */
    void notify(final String message);
}
