package de.berlios.jalister.model.transformers;

import de.berlios.jalister.model.metadata.FileEntryMetaData;

import java.util.*;

/**
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 19.08.2007 2:33:37
 */
public class SortedMetaDataTransformer implements Transformer<Collection<FileEntryMetaData>, Collection<FileEntryMetaData>> {
    /**
     * Field COMPARATOR
     */
    private static final Comparator<? super FileEntryMetaData> COMPARATOR = new Comparator<FileEntryMetaData>() {

        public int compare(final FileEntryMetaData o1, final FileEntryMetaData o2) {
            return o1.getKey().getName().compareTo(o2.getKey().getName());
        }
    };

    public Collection<FileEntryMetaData> transform(final Collection<FileEntryMetaData> fileEntryMetaDatas) {
        final List<FileEntryMetaData> result = new LinkedList<FileEntryMetaData>(fileEntryMetaDatas);
        Collections.sort(result, COMPARATOR);
        return result;
    }
}
