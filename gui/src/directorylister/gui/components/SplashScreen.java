package directorylister.gui.components;

import directorylister.startup.ProgressNotifier;

import java.awt.HeadlessException;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:19:00
 */
public class SplashScreen extends ProgressBarFrame {

    /**
     * Constructs a new SplashScreen.
     *
     * @throws java.awt.HeadlessException
     */
    public SplashScreen() throws HeadlessException {

    }

    /**
     * Method main ...
     *
     * @param args of type String[]
     */
    public static void main(String[] args) {
        SplashScreen screen = new SplashScreen();
        screen.setVisible(true);
        ProgressNotifier notifier = new ProgressNotifier();
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
