package directorylister.model.metadata.key;

import directorylister.model.Nameable;
import directorylister.model.metadata.MetadataProviderFactory;

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
     * @see Nameable#getName()
     */
    public String getName() {
        return name;
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getName();
    }

    /** {@inheritDoc} */
    public String getLocalizationKey() {
        return "MetaDataKey." + name;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final DefaultMetaDataKey that = (DefaultMetaDataKey) obj;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
