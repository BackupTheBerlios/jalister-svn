package de.berlios.jalister.model.metadata;

import de.berlios.jalister.model.metadata.key.MetaDataKey;
import de.berlios.jalister.model.metadata.provider.MetadataProvider;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 20:36:23
 */
public class MetadataProviderFactory {
    /**
     * Field INSTANCE
     */
    private static final MetadataProviderFactory INSTANCE = new MetadataProviderFactory();
    /**
     * Field providers
     */
    private Collection<MetadataProvider> providers = Collections.synchronizedList(new LinkedList<MetadataProvider>());
    /**
     * Field keys
     */
    private Collection<MetaDataKey> keys = Collections.synchronizedCollection(new LinkedList<MetaDataKey>());

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

    public void addMetadataKey(final MetaDataKey metaDataKey) {
        if (!keys.contains(metaDataKey)) {
            keys.add(metaDataKey);
        }
    }

    public Collection<MetaDataKey> getMetaDataKeys() {
        return Collections.unmodifiableCollection(keys);
    }
}
