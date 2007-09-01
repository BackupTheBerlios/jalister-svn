package directorylister.model.metadata;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 19.08.2007 2:45:15
 */
public class FileNameMetaDataValue extends DefaultMetaDataValue<String> {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 4231743953568076172L;

    public FileNameMetaDataValue(final String absolutePath) {
        super(absolutePath);
    }
}
