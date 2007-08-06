package directorylister.model.transformers;

import directorylister.model.FileEntry;
import directorylister.model.FileEntryComparator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 0:04:00
 */
public class SortedFileEntryListTransformer implements Transformer<List<FileEntry>, List<FileEntry>> {

    /**
     * Field TRANSFORMER
     */
    public static final SortedFileEntryListTransformer TRANSFORMER = new SortedFileEntryListTransformer();


    /**
     * Do not instantiate SortedFileEntryListTransformer.
     */
    private SortedFileEntryListTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public List<FileEntry> transform(final List<FileEntry> childs) {
        List<FileEntry> result = new LinkedList<FileEntry>(childs);
        Collections.sort(result, FileEntryComparator.COMPARATOR);
        return result;
    }
}
