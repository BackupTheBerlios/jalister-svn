package directorylister.model.metadata.value;

import java.util.Date;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 21:05:06
 */
public class FileLastModifiedMetaDataValue extends DefaultMetaDataValue<Date> {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 1219662304776748985L;

    public FileLastModifiedMetaDataValue(final long lastModified) {
        super(new Date(lastModified));
    }
}
