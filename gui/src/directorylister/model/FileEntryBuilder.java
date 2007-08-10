package directorylister.model;

import java.io.File;
import java.io.IOException;

/**
 * Builder for FileEntry.
 *
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:43:41
 */
public class FileEntryBuilder {


    /**
     * Constructs a new FileEntryBuilder.
     */
    public FileEntryBuilder() {
    }

    /**
     * Builds FileEntry for the given <code>File</code> object.
     *
     * @param file for which build FileEntry.
     * @return new FileEntry for the passed file.
     * @throws IOException - if I/O error occures.
     */
    public FileEntry buildFrom(final File file) {
        final String md5 = null; //directorylister.utils.MD5Hasher.getMD5(file);
        return new FileEntry(file.getAbsolutePath(), FileType.getType(file),
                file.lastModified(), md5, file.getName());
    }
}
