package directorylister;

import directorylister.gui.MainWindow;
import directorylister.gui.components.SplashScreen;
import directorylister.resources.Localizer;
import directorylister.resources.ResourceHandler;
import directorylister.startup.StartupProgressNotifier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;

/**
 * User: ginnungagap
 */
public class Main {

    private static final Log logger = LogFactory.getLog(Main.class);

    public static void main(String[] args) {

        StartupProgressNotifier
                progressNotifier = new StartupProgressNotifier();

        final SplashScreen splashScreen = initializeSplash();
        progressNotifier.addListener(splashScreen);

        ResourceHandler.getInstance();
        progressNotifier.notifyListeners("Startup.LoadingResources");


        progressNotifier.notifyListeners("Startup.InitGUI");
        initializeGUI();


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

    private static void initializeGUI() {
        final MainWindow mainWindow = new MainWindow();

        SwingUtilities.invokeLater(new Runnable() {
            /**
             * {@inheritDoc}
             */
            public void run() {
                mainWindow.setLocationRelativeTo(null);

                final Localizer localizer = new Localizer();
                localizer.localize(mainWindow);

                mainWindow.setVisible(true);
            }
        });
    }

    private static SplashScreen initializeSplash() {
        final SplashScreen splashScreen = new SplashScreen();
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * {@inheritDoc}
             */
            public void run() {
                splashScreen.setVisible(true);
            }
        });
        return splashScreen;
    }
}
