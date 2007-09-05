package directorylister.parser;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

/**
 * @author schakal Oleg Atamanenko
 * @since 05.09.2007 4:32:27
 */
public class FileComparator implements Comparator<File>, Serializable {
    public int compare(final File o1, final File o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
