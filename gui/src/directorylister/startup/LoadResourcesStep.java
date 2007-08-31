package directorylister.startup;

import directorylister.notification.ProgressNotifier;
import directorylister.resources.ResourceHandler;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:43:39
 */
public class LoadResourcesStep extends AbstractStartupStep {
    public LoadResourcesStep(final ProgressNotifier progressNotifier) {
        super(progressNotifier);
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        ResourceHandler.getInstance();
        progressNotifier.notifyListeners("Startup.LoadingResources");
    }
}
