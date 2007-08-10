package directorylister.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 16.07.2007 23:58:49
 */
public class FileEntryComparator implements Comparator<FileEntry>, Serializable {

    /**
     * Field COMPARATOR
     */
    public static final FileEntryComparator COMPARATOR = new FileEntryComparator();
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -7452687270053312042L;


    /**
     * Do not instantiate FileEntryComparator.
     */
    private FileEntryComparator() {

    }

    /**
     * {@inheritDoc}
     */
    public int compare(FileEntry firstEntry, FileEntry secondEntry) {
        // TODO: check this code.
        if (firstEntry.getFileType().equals(FileType.DIRECTORY) && secondEntry.getFileType().equals(FileType.DIRECTORY)) {
            return firstEntry.getShortName().compareTo(secondEntry.getShortName());
        } else
        if (!firstEntry.getFileType().equals(FileType.DIRECTORY) && !secondEntry.getFileType().equals(FileType.DIRECTORY)) {
            return firstEntry.getShortName().compareTo(secondEntry.getShortName());
        } else if (firstEntry.getFileType().equals(FileType.DIRECTORY)) {
            return -1;
        } else {
            return 1;
        }
    }
}
