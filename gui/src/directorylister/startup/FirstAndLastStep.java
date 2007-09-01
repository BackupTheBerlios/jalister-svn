package directorylister.startup;

import directorylister.gui.components.SplashScreen;
import directorylister.notification.ProgressNotifier;

import javax.swing.SwingUtilities;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:49:54
 */
public class FirstAndLastStep extends AbstractStartupStep {
    /**
     * Field splashScreen
     */
    private SplashScreen splashScreen = null;
    /**
     * Field wasRun
     */
    private boolean wasRun;

    public FirstAndLastStep(final ProgressNotifier progressNotifier) {
        super(progressNotifier);
        wasRun = false;
    }


    /**
     * {@inheritDoc}
     */
    public void execute() {
        if (!wasRun) {
            showSplashScreen();
        }
        else {
            hideSplashScreen();
        }
    }

    private void hideSplashScreen() {
        progressNotifier.notifyListeners("Startup.Done");
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * {@inheritDoc}
             */
            public void run() {
                splashScreen.dispose();
            }
        });
    }

    private void showSplashScreen() {
        splashScreen = new SplashScreen();
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * {@inheritDoc}
             */
            public void run() {
                splashScreen.setVisible(true);
            }
        });
        progressNotifier.addListener(splashScreen);
        wasRun = true;
    }

}
