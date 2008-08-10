package de.berlios.jalister.startup;

import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.ResourceHandler;
import de.berlios.jalister.utils.SwingUtils;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:41:08
 */
public class BaseInitialization extends AbstractStartupStep {
    public BaseInitialization(final ProgressNotifier progressNotifier) {
        super(progressNotifier);
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        final ResourceHandler resourceHandler = ResourceHandler.getInstance();
        progressNotifier.notifyListeners(new Notification(resourceHandler.getMessage("Startup.BaseInitialization")));

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread t, Throwable e) {
                // TODO: Localize message.
                SwingUtils.showError("Thread " + t.getName() + " throw uncaught error: " + e.getMessage());
            }
        });

    }

}
