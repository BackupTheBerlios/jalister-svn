package de.berlios.jalister;

import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.startup.*;
import org.apache.commons.logging.Log;
import static org.apache.commons.logging.LogFactory.getLog;

/**
 * User: ginnungagap
 */
public class Main {

    /**
     * Field logger
     */
    private static final Log logger = getLog(Main.class);

    /**
     * Method main ...
     *
     * @param args of type String[]
     */
    public static void main(final String[] args) {

        final StartupProcess startupProcess = new StartupProcess();
        final ProgressNotifier progressNotifier = new ProgressNotifier();

        final FirstAndLastStep firstAndLastStep = new FirstAndLastStep(progressNotifier);
        startupProcess.setFirstStep(firstAndLastStep);
        startupProcess.setLastStep(firstAndLastStep);

        startupProcess.addStep(new BaseInitialization(progressNotifier));
        startupProcess.addStep(new LoadResourcesStep(progressNotifier));
        startupProcess.addStep(new LoadThemeStep(progressNotifier));
        startupProcess.addStep(new InitializeMetadataStep(progressNotifier));
        startupProcess.addStep(new InitializeGUIStep(progressNotifier));

        startupProcess.execute();
        logger.info("Application successfully started.");
    }


}
