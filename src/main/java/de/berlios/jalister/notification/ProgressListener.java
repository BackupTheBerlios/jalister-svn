package de.berlios.jalister.notification;

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
     * @param notification - notification message.
     */
    void notify(final Notification notification);
}
