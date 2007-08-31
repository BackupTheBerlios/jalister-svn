package directorylister.model.metadata;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 21:06:30
 */
public class DefaultMetaDataKey extends AbstractSearchableMetaDataKey {
    private static final long serialVersionUID = -5600589833389116491L;

    public DefaultMetaDataKey(final String name) {
        this.name = name;

        MetadataProviderFactory.getInstance().addMetadataKey(this);
    }

    private final String name;

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return getName();
    }

    public String getLocalizationKey() {
        return "MetaDataKey." + name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultMetaDataKey that = (DefaultMetaDataKey) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }
}
