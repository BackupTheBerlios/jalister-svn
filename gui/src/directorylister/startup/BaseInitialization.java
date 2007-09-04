package directorylister.startup;

import directorylister.notification.Notification;
import directorylister.notification.ProgressNotifier;
import directorylister.resources.ResourceHandler;
import directorylister.utils.SwingUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Category;
import org.apache.log4j.Level;

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
        BasicConfigurator.configure();
        Category.getRoot().setLevel(Level.DEBUG);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread t, Throwable e) {
                // TODO: Localize message.
                SwingUtils.showError("Thread " + t.getName() + " throw uncaught error: " + e.getMessage());
            }
        });

    }

}
