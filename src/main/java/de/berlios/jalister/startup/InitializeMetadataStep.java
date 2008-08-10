package de.berlios.jalister.startup;

import de.berlios.jalister.model.metadata.MetadataProviderFactory;
import de.berlios.jalister.model.metadata.provider.GenericInfoMetaDataProvider;
import de.berlios.jalister.model.metadata.provider.MimeTypeProvider;
import de.berlios.jalister.model.metadata.provider.Mp3TagProvider;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.ResourceHandler;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:47:08
 */
public class InitializeMetadataStep extends AbstractStartupStep {
    public InitializeMetadataStep(final ProgressNotifier progressNotifier) {
        super(progressNotifier);
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        final String message = ResourceHandler.getInstance().getMessage("Startup.InitMetaData");
        progressNotifier.notifyListeners(new Notification(message));
        final MetadataProviderFactory metadataProviderFactory = MetadataProviderFactory.getInstance();
        metadataProviderFactory.addMetadataProvider(new GenericInfoMetaDataProvider());
        metadataProviderFactory.addMetadataProvider(new MimeTypeProvider());
        metadataProviderFactory.addMetadataProvider(new Mp3TagProvider());
    }
}
