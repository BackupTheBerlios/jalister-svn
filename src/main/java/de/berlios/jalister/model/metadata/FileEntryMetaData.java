package de.berlios.jalister.model.metadata;

import de.berlios.jalister.model.metadata.key.MetaDataKey;
import de.berlios.jalister.model.metadata.value.MetaDataValue;
import de.berlios.jalister.utils.ObjectUtils;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.Serializable;


/**
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.07.2007 1:34:41
 */
@Root
public class FileEntryMetaData implements Serializable {
    /**
     * Field key
     */
    @Attribute
    private MetaDataKey key;
    /**
     * Field value
     */
    @Attribute
    private MetaDataValue value;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -7192341076454468080L;


    /**
     * Getter for property 'key'.
     *
     * @return Value for property 'key'.
     */
    public MetaDataKey getKey() {
        return key;
    }

    /**
     * Setter for property 'key'.
     *
     * @param key Value to set for property 'key'.
     */
    public void setKey(final MetaDataKey key) {
        this.key = key;
    }

    /**
     * Getter for property 'value'.
     *
     * @return Value for property 'value'.
     */
    public MetaDataValue getValue() {
        return value;
    }

    /**
     * Setter for property 'value'.
     *
     * @param value Value to set for property 'value'.
     */
    public void setValue(final MetaDataValue value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override()
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final FileEntryMetaData that = (FileEntryMetaData) o;

        return key == that.key && !(value != null ? !value.equals(that.value) : that.value != null);

    }

    /**
     * {@inheritDoc}
     */
    @Override()
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override()
    public String toString() {
        return ObjectUtils.buildToString(this);
    }
}
