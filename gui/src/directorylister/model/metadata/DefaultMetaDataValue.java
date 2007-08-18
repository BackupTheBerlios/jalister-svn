package directorylister.model.metadata;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 21:11:20
 */
public class DefaultMetaDataValue<T> implements MetaDataValue<T> {
    private final T value;

    public DefaultMetaDataValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    public T getValue() {
        return value;
    }
}
