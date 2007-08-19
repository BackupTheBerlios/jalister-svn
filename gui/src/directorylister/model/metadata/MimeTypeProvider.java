package directorylister.model.metadata;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author schakal Oleg Atamanenko
 * @since 19.08.2007 12:14:35
 */
// TODO: Improve capabilities of the code.
public class MimeTypeProvider implements MetadataProvider {
    public Collection<FileEntryMetaData> getMetadata(final File file) {
        final String mimeType;
        if (file.isDirectory()) {
            mimeType = "Directory";
        }
        else {
            mimeType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file);
        }

        final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
        fileEntryMetaData.setKey(new MimeTypeMetaDataKey());
        fileEntryMetaData.setValue(new MimeTypeMetaDataValue(mimeType));
        return Arrays.asList(fileEntryMetaData);
    }
}
