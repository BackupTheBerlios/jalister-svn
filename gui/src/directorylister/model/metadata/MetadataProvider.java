package directorylister.model.metadata;

import java.io.File;
import java.util.Collection;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 20:36:08
 */
public interface MetadataProvider {

    Collection<FileEntryMetaData> getMetadata(final File file);
}
