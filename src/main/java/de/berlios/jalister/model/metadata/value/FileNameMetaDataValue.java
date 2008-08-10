package de.berlios.jalister.model.metadata.value;

/**
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
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
