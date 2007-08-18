package directorylister.model.metadata;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 19.08.2007 2:45:15
 */
public class FileNameMetaDataValue extends DefaultMetaDataValue<String>{
    public FileNameMetaDataValue(final String absolutePath) {
        super(absolutePath);
    }
}
