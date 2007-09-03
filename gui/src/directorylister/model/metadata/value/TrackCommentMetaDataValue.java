package directorylister.model.metadata.value;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 11:42:22
 */
public class TrackCommentMetaDataValue extends DefaultMetaDataValue<String> {
    private static final long serialVersionUID = -1261084425105917258L;

    public TrackCommentMetaDataValue(final String trackComment) {
        super(trackComment);
    }
}
