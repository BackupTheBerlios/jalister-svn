package directorylister.gui.components;

import directorylister.notification.ProgressNotifier;

import java.awt.HeadlessException;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
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
     * @throws java.awt.HeadlessException in case of error.
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

        notifier.notifyListeners("Starting up...", false);

        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        notifier.notifyListeners("Done...", false);
    }
}
