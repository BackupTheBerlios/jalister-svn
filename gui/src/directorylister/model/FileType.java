package directorylister.model;

import directorylister.io.FileUtils;

import java.io.File;

/**
 * Represents different file types.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 11.08.2007 4:39:02
 */
public enum FileType {
    /**
     * Field FILE
     */FILE,
    /**
     * Field DIRECTORY
     */DIRECTORY,
    /**
     * Field SYMLINK
     */SYMLINK;

    /**
     * Detects type of the file.
     *
     * @param file for test.
     * @return FileType for the passed file.
     */
    public static FileType getType(final File file) {
        if (FileUtils.isLink(file)) {
            return SYMLINK;
        }
        else if (file.isDirectory()) {
            return DIRECTORY;
        }
        else if (file.isFile()) {
            return FILE;
        }
        return FILE;
    }
}
