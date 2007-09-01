package directorylister.model;

import directorylister.model.metadata.FileEntryMetaData;
import directorylister.model.metadata.MetadataProvider;
import directorylister.model.metadata.MetadataProviderFactory;

import java.io.File;
import java.util.Collection;

/**
 * Builder for FileEntry.
 *
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:43:41
 */
public class FileEntryBuilder {
    /**
     * Field availableProviders
     */
    private Collection<MetadataProvider> availableProviders;


    /**
     * Constructs a new FileEntryBuilder.
     */
    public FileEntryBuilder() {
        availableProviders = MetadataProviderFactory.getInstance().getAvailableProviders();
    }

    /**
     * Builds FileEntry for the given <code>File</code> object.
     *
     * @param file for which build FileEntry.
     * @return new FileEntry for the passed file.
     */
    public FileEntry buildFrom(final File file) {
        final String md5 = null; //directorylister.utils.MD5Hasher.getMD5(file);
        final FileEntry fileEntry = new FileEntry(file.getAbsolutePath(), FileType.getType(file),
                md5, file.getName());
        for (final MetadataProvider availableProvider : availableProviders) {
            final Collection<FileEntryMetaData> metadata = availableProvider.getMetadata(file);
            fileEntry.addMetaDatas(metadata);
        }
        return fileEntry;
    }
}
