package directorylister;

import directorylister.gui.MainWindow;
import directorylister.gui.components.SplashScreen;
import directorylister.model.metadata.GenericInfoMetaDataProvider;
import directorylister.model.metadata.MetadataProviderFactory;
import directorylister.notification.ProgressNotifier;
import directorylister.resources.Localizer;
import directorylister.resources.ResourceHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import javax.swing.SwingUtilities;

/**
 * User: ginnungagap
 */
public class Main {

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(Main.class);

    /**
     * Method main ...
     *
     * @param args of type String[]
     */
    public static void main(final String[] args) {

        BasicConfigurator.configure();

        final ProgressNotifier
                progressNotifier = new ProgressNotifier();

        final SplashScreen splashScreen = initializeSplash();
        progressNotifier.addListener(splashScreen);

        ResourceHandler.getInstance();
        progressNotifier.notifyListeners("Startup.LoadingResources", true);

        progressNotifier.notifyListeners("Startup.InitMetaData", true);
        initializeMetaDataSuppor();

        progressNotifier.notifyListeners("Startup.InitGUI", true);
        initializeGUI();

        progressNotifier.notifyListeners("Startup.Done", true);
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * {@inheritDoc}
             */
            public void run() {
                splashScreen.dispose();
            }
        });

    }

    private static void initializeMetaDataSuppor() {
        final MetadataProviderFactory metadataProviderFactory = MetadataProviderFactory.getInstance();
        metadataProviderFactory.addMetadataProvider(new GenericInfoMetaDataProvider());
    }

    /**
     * Method initializeGUI ...
     */
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

    /**
     * Method initializeSplash ...
     *
     * @return SplashScreen
     */
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
