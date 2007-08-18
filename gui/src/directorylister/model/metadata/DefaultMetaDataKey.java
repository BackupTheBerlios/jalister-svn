package directorylister.model.metadata;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 21:06:30
 */
public class DefaultMetaDataKey implements MetaDataKey {
    public DefaultMetaDataKey(final String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return getName();
    }
}
