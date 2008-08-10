package de.berlios.jalister.model.transformers;

import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.FileEntryComparator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
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
        final List<FileEntry> result = new LinkedList<FileEntry>(childs);
        Collections.sort(result, FileEntryComparator.COMPARATOR);
        return result;
    }
}
