package directorylister.model.metadata;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 21:06:30
 */
public class DefaultMetaDataKey extends AbstractSearchableMetaDataKey {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -5600589833389116491L;

    public DefaultMetaDataKey(final String name) {
        this.name = name;

        MetadataProviderFactory.getInstance().addMetadataKey(this);
    }

    /**
     * Field name
     */
    private final String name;

    /**
     * @see directorylister.model.Nameable#getName()
     */
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DefaultMetaDataKey that = (DefaultMetaDataKey) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
