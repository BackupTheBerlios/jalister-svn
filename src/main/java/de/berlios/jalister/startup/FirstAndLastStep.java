package de.berlios.jalister.startup;

import de.berlios.jalister.gui.components.SplashScreen;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.ResourceHandler;

import java.awt.*;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:49:54
 */
public class FirstAndLastStep extends AbstractStartupStep {
    /**
     * Field splashScreen
     */
    private SplashScreen splashScreen;
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
        } else {
            hideSplashScreen();
        }
    }

    private void hideSplashScreen() {
        progressNotifier.notifyListeners(new Notification(ResourceHandler.getInstance().getMessage("Startup.Done")));
        EventQueue.invokeLater(new Runnable() {
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
        EventQueue.invokeLater(new Runnable() {
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
