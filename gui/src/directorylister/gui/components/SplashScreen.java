package directorylister.gui.components;

import directorylister.notification.Notification;
import directorylister.notification.ProgressNotifier;
import directorylister.resources.ResourceHandler;

import java.awt.HeadlessException;

/**
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.07.2007 1:19:00
 */
public class SplashScreen extends ProgressBarFrame {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -9221290082431846456L;

    /**
     * Constructs a new SplashScreen.
     *
     * @throws HeadlessException in case of error.
     */
    public SplashScreen() throws HeadlessException {

    }

    /**
     * Method main ...
     *
     * @param args of type String[]
     */
    public static void main(final String[] args) {
        final SplashScreen screen = new SplashScreen();
        screen.setVisible(true);
        final ProgressNotifier notifier = new ProgressNotifier();
        notifier.addListener(screen);

        final ResourceHandler resourceHandler = ResourceHandler.getInstance();

        notifier.notifyListeners(new Notification(resourceHandler.getMessage("Startup.Start")));

        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        notifier.notifyListeners(new Notification(resourceHandler.getMessage("Startup.Done")));
    }
}
