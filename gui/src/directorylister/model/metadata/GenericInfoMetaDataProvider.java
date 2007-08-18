package directorylister.model.metadata;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Provides generic information about file.
 *
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 20:48:46
 */
public class GenericInfoMetaDataProvider implements MetadataProvider {

    public GenericInfoMetaDataProvider() {

    }

    public Collection<FileEntryMetaData> getMetadata(final File file) {
        final LinkedList<FileEntryMetaData> list = new LinkedList<FileEntryMetaData>();

        list.add(getFileName(file));
        list.add(getFileSize(file));
        list.add(getFileModificationDate(file));
        return list;
    }

    private FileEntryMetaData getFileName(final File file) {
        final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
        fileEntryMetaData.setKey(new FileNameMetaDataKey());
        fileEntryMetaData.setValue(new FileNameMetaDataValue(file.getAbsolutePath()));
        return fileEntryMetaData;
    }

    private FileEntryMetaData getFileModificationDate(final File file) {
        final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
        fileEntryMetaData.setKey(new FileLastModifiedMetaDataKey());
        fileEntryMetaData.setValue(new FileLastModifiedMetaDataValue(file.lastModified()));
        return fileEntryMetaData;
    }

    private FileEntryMetaData getFileSize(final File file) {
        final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
        fileEntryMetaData.setKey(new FileSizeMetaDataKey());
        fileEntryMetaData.setValue(new FileSizeMetaDataValue(file.length()));
        return fileEntryMetaData;
    }

}
