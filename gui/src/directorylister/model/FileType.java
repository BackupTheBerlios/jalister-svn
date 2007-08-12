package directorylister.model;

import directorylister.io.FileUtils;

import java.io.File;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 11.08.2007 4:39:02
 */
public enum FileType {
    FILE,
    DIRECTORY,
    SYMLINK;

    public static FileType getType(final File file) {
        if (FileUtils.isLink(file)) {
            return SYMLINK;
        } else if (file.isDirectory()) {
            return DIRECTORY;
        } else if (file.isFile()) {
            return FILE;
        }
        return FILE;
    }
}
