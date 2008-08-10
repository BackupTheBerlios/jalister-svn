package de.berlios.jalister.startup;

import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.ResourceHandler;

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
        final ResourceHandler resourceHandler = ResourceHandler.getInstance();
        progressNotifier.notifyListeners(new Notification(resourceHandler.getMessage("Startup.LoadingResources")));
    }
}
