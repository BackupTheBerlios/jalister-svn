package directorylister.search;

import org.apache.lucene.document.Field;

/**
 * Represents search field for searcher.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 05.08.2007 1:13:15
 */
public enum SearchField {
    /**
     * Field ID
     */ID(Field.Store.COMPRESS, Field.Index.UN_TOKENIZED),
    /**
     * Field FILE_NAME
     */FILE_NAME(Field.Store.YES, Field.Index.TOKENIZED),
    /**
     * Field SHORT_NAME
     */SHORT_NAME(Field.Store.YES, Field.Index.TOKENIZED);

    /**
     * Field store
     */
    private final Field.Store store;
    /**
     * Field index
     */
    private final Field.Index index;

    /**
     * Constructor SearchField creates a new SearchField instance.
     *
     * @param store of type Store
     * @param index of type Index
     */
    SearchField(final Field.Store store, final Field.Index index) {
        this.store = store;
        this.index = index;
    }

    /**
     * Getter for property 'store'.
     *
     * @return Value for property 'store'.
     */
    public Field.Store getStore() {
        return store;
    }

    /**
     * Getter for property 'index'.
     *
     * @return Value for property 'index'.
     */
    public Field.Index getIndex() {
        return index;
    }

    /**
     * Method createField ...
     *
     * @param value of type String
     * @return Field
     */
    public Field createField(final String value) {
        return new Field(name(), value, getStore(), getIndex());
    }
}
