package directorylister;

import directorylister.notification.ProgressNotifier;
import directorylister.startup.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

        StartupProcess startupProcess = new StartupProcess();
        final ProgressNotifier progressNotifier = new ProgressNotifier();

        final FirstAndLastStep firstAndLastStep = new FirstAndLastStep(progressNotifier);
        startupProcess.setFirstStep(firstAndLastStep);
        startupProcess.setLastStep(firstAndLastStep);

        startupProcess.addStep(new InitializeLoggerStep(progressNotifier));
        startupProcess.addStep(new LoadResourcesStep(progressNotifier));
        startupProcess.addStep(new LoadThemeStep(progressNotifier));
        startupProcess.addStep(new InitializeMetadataStep(progressNotifier));
        startupProcess.addStep(new InitializeGUIStep(progressNotifier));

        startupProcess.execute();
        logger.info("Application successfully started.");
    }


}
