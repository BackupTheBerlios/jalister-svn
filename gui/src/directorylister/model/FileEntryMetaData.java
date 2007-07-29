package directorylister.model;

import directorylister.utils.ObjectUtils;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:34:41
 */
public class FileEntryMetaData implements Serializable {
    MetaDataKey key;
    MetaDataValue value;


    /**
     * Constructs a new FileEntryMetaData.
     */
    public FileEntryMetaData() {
    }


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
    public void setKey(MetaDataKey key) {
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
    public void setValue(MetaDataValue value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override()
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileEntryMetaData that = (FileEntryMetaData) o;

        if (key != that.key) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override()
    public int hashCode() {
        int result;
        result = (key != null ? key.hashCode() : 0);
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
