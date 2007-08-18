package directorylister.model.metadata;

import java.util.Date;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 21:05:06
 */
public class FileLastModifiedMetaDataValue extends DefaultMetaDataValue<Date> {
    public FileLastModifiedMetaDataValue(final long l) {
        super(new Date(l));
    }
}
