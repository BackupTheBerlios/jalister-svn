package directorylister.model.metadata;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 20:56:10
 */
public class FileSizeMetaDataValue extends DefaultMetaDataValue<Long> {
    private static final long serialVersionUID = 7980462317011099619L;

    public FileSizeMetaDataValue(final long fileSize) {
        super(fileSize);
    }
}
