package directorylister.model.transformers;

import directorylister.model.metadata.FileEntryMetaData;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
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
