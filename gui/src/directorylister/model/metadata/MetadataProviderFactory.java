package directorylister.model.metadata;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 20:36:23
 */
public class MetadataProviderFactory {
    private static final MetadataProviderFactory INSTANCE = new MetadataProviderFactory();
    private Collection<MetadataProvider> providers = Collections.synchronizedList(new LinkedList<MetadataProvider>());

    private MetadataProviderFactory() {
    }

    public static MetadataProviderFactory getInstance() {
        return INSTANCE;
    }

    public Collection<MetadataProvider> getAvailableProviders() {
        return Collections.unmodifiableCollection(providers);
    }

    public void addMetadataProvider(final MetadataProvider metadataProvider) {
        providers.add(metadataProvider);
    }
}
