package directorylister.startup;

import directorylister.model.metadata.provider.GenericInfoMetaDataProvider;
import directorylister.model.metadata.MetadataProviderFactory;
import directorylister.model.metadata.provider.MimeTypeProvider;
import directorylister.model.metadata.provider.Mp3TagProvider;
import directorylister.notification.ProgressNotifier;

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
        progressNotifier.notifyListeners("Startup.InitMetaData");
        final MetadataProviderFactory metadataProviderFactory = MetadataProviderFactory.getInstance();
        metadataProviderFactory.addMetadataProvider(new GenericInfoMetaDataProvider());
        metadataProviderFactory.addMetadataProvider(new MimeTypeProvider());
        metadataProviderFactory.addMetadataProvider(new Mp3TagProvider());
    }
}
