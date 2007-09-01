package directorylister.model.metadata.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.v1.ID3V1Tag;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import directorylister.model.metadata.FileEntryMetaData;
import directorylister.model.metadata.key.ArtistMetaDataKey;
import directorylister.model.metadata.value.ArtistMetaDataValue;
import directorylister.model.metadata.key.AlbumMetaDataKey;
import directorylister.model.metadata.value.AlbumMetaDataValue;
import directorylister.model.metadata.key.GenreMetaDataKey;
import directorylister.model.metadata.value.GenreMetaDataValue;
import directorylister.model.metadata.key.YearMetaDataKey;
import directorylister.model.metadata.key.TrackCommentMetaDataKey;
import directorylister.model.metadata.value.YearMetaDataValue;
import directorylister.model.metadata.key.TrackTitleMetaDataKey;
import directorylister.model.metadata.value.TrackTitleMetaDataValue;
import directorylister.model.metadata.value.TrackCommentMetaDataValue;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 11:36:26
 */
public class Mp3TagProvider implements MetadataProvider {
    private static final Log logger = LogFactory.getLog(Mp3TagProvider.class.getName());

    public Collection<FileEntryMetaData> getMetadata(final File file) {
        final Collection<FileEntryMetaData> metaDatas = new LinkedList<FileEntryMetaData>();
        final MP3File mp3File = new MP3File(file);
        try {
            final ID3V1Tag id3V1Tag = mp3File.getID3V1Tag();

            if (null == id3V1Tag) {
                return null;
            }

            final FileEntryMetaData artist = new FileEntryMetaData();
            artist.setKey(new ArtistMetaDataKey());
            artist.setValue(new ArtistMetaDataValue(id3V1Tag.getArtist()));
            metaDatas.add(artist);

            final FileEntryMetaData album = new FileEntryMetaData();
            album.setKey(new AlbumMetaDataKey());
            album.setValue(new AlbumMetaDataValue(id3V1Tag.getAlbum()));
            metaDatas.add(album);

            final FileEntryMetaData genre = new FileEntryMetaData();
            genre.setKey(new GenreMetaDataKey());
            genre.setValue(new GenreMetaDataValue(id3V1Tag.getGenre().toString()));
            metaDatas.add(genre);

            final FileEntryMetaData year = new FileEntryMetaData();
            year.setKey(new YearMetaDataKey());
            year.setValue(new YearMetaDataValue(id3V1Tag.getYear()));
            metaDatas.add(year);

            final FileEntryMetaData title = new FileEntryMetaData();
            title.setKey(new TrackTitleMetaDataKey());
            title.setValue(new TrackTitleMetaDataValue(id3V1Tag.getTitle()));
            metaDatas.add(title);

            final FileEntryMetaData comment = new FileEntryMetaData();
            comment.setKey(new TrackCommentMetaDataKey());
            comment.setValue(new TrackCommentMetaDataValue(id3V1Tag.getComment()));
            metaDatas.add(comment);

        }
        catch(ID3Exception e) {
            logger.warn(e.toString());
        }
        return metaDatas;
    }
}
