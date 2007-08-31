package directorylister.model.metadata;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Field;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 10:02:36
 */
public abstract class AbstractSearchableMetaDataKey implements SearchableMetaDataKey {
    /**
     * {@inheritDoc}
     */
    public Field.Store getStore() {
        return Field.Store.YES;
    }

    /**
     * {@inheritDoc}
     */
    public Field.Index getIndex() {
        return Field.Index.TOKENIZED;
    }

    /**
     * {@inheritDoc}
     */
    public Field createField(final String value) {
        if (!StringUtils.isEmpty(value)) {
            return new Field(getName(), value, getStore(), getIndex());
        }
        return null;
    }
}
