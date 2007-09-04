package directorylister.model.metadata.provider;

import directorylister.model.metadata.FileEntryMetaData;

import java.io.File;
import java.util.Collection;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 20:36:08
 */
public interface MetadataProvider {

    /**
     * {@inheritDoc}
     */
    Collection<FileEntryMetaData> getMetadata(final File file);
}
