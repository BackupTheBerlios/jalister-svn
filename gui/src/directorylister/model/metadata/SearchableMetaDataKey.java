package directorylister.model.metadata;

import org.apache.lucene.document.Field;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 10:00:13
 */
public interface SearchableMetaDataKey extends MetaDataKey {
    /**
     * Getter for property 'store'.
     *
     * @return Value for property 'store'.
     */
    public Field.Store getStore();

    /**
     * Getter for property 'index'.
     *
     * @return Value for property 'index'.
     */
    public Field.Index getIndex();

    /**
     * Method createField ...
     *
     * @param value of type String
     * @return Field
     */
    public Field createField(final String value);


}
