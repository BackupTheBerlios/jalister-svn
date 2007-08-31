package directorylister.startup;

import directorylister.notification.ProgressNotifier;
import org.apache.log4j.BasicConfigurator;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:41:08
 */
public class InitializeLoggerStep extends AbstractStartupStep {
    public InitializeLoggerStep(final ProgressNotifier progressNotifier) {
        super(progressNotifier);
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        BasicConfigurator.configure();
    }

}
