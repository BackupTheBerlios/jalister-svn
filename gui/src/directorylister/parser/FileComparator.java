package directorylister.parser;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator for java.io.File objects.
 *
 * @author schakal Oleg Atamanenko
 * @since 05.09.2007 4:32:27
 */
public class FileComparator implements Comparator<File>, Serializable {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 3540315946180762846L;

    /**
     * {@inheritDoc}
     */
    public int compare(final File o1, final File o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
