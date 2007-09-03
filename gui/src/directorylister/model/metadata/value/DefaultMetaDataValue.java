package directorylister.model.metadata.value;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 21:11:20
 */
public class DefaultMetaDataValue<T> implements MetaDataValue<T> {
    /**
     * Field value
     */
    private final T value;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -6052472009022096426L;

    public DefaultMetaDataValue(final T value) {
        this.value = value;
    }

    public String getFormattedValue() {
        return String.valueOf(getValue());
    }

    @Override
    public String toString() {
        return getFormattedValue();
    }

    public T getValue() {
        return value;
    }
}
