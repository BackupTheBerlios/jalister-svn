package de.berlios.jalister.model.metadata.provider;

import de.berlios.jalister.model.metadata.FileEntryMetaData;
import de.berlios.jalister.model.metadata.key.*;
import de.berlios.jalister.model.metadata.value.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.v1.ID3V1Tag;
import org.blinkenlights.jid3.v2.ID3V2Tag;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 11:36:26
 */
public class Mp3TagProvider implements MetadataProvider {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(Mp3TagProvider.class.getName());

    /**
     * Constructor Mp3TagProvider creates a new Mp3TagProvider instance.
     */
    public Mp3TagProvider() {
    }

    /**
     * @see MetadataProvider#getMetadata(File)
     */
    public Collection<FileEntryMetaData> getMetadata(final File file) {
        if (file.isDirectory() || file.length() == 0) {
            return Collections.emptySet();
        }
        final Collection<FileEntryMetaData> metaDatas = new LinkedList<FileEntryMetaData>();
        final MP3File mp3File = new MP3File(file);
        final ID3V1Tag id3V1Tag = getId3V1Tag(mp3File);
        if (null != id3V1Tag) {
            addArtistMetadata(metaDatas, id3V1Tag.getArtist());
            addAlbumMetadata(metaDatas, id3V1Tag.getAlbum());
            addGenreMetadata(metaDatas, id3V1Tag.getGenre().toString());
            addYearMetadata(metaDatas, id3V1Tag.getYear());
            addTitleMetadata(metaDatas, id3V1Tag.getTitle());
            addCommentMetadata(metaDatas, id3V1Tag.getComment());
        }

        final ID3V2Tag id3V2Tag = getId3V2Tag(mp3File);
        if (null != id3V2Tag) {
            addArtistMetadata(metaDatas, id3V2Tag.getArtist());
            addAlbumMetadata(metaDatas, id3V2Tag.getAlbum());
            addGenreMetadata(metaDatas, id3V2Tag.getGenre());

            try {
                addYearMetadata(metaDatas, String.valueOf(id3V2Tag.getYear()));
            }
            catch (ID3Exception e) {
                logger.warn(e.toString());
            }

            addTitleMetadata(metaDatas, id3V2Tag.getTitle());
            addCommentMetadata(metaDatas, id3V2Tag.getComment());
        }
        return metaDatas;
    }

    /**
     * Method getId3V2Tag ...
     *
     * @param mp3File of type MP3File
     * @return ID3V2Tag
     */
    private static ID3V2Tag getId3V2Tag(final MP3File mp3File) {
        try {
            return mp3File.getID3V2Tag();
        }
        catch (ID3Exception e) {
            logger.warn("Cannot read ID3V2 tag from " + mp3File, e);
            return null;
        }
    }

    /**
     * Method getId3V1Tag ...
     *
     * @param mp3File of type MP3File
     * @return ID3V1Tag
     */
    private static ID3V1Tag getId3V1Tag(final MP3File mp3File) {
        try {
            return mp3File.getID3V1Tag();
        }
        catch (ID3Exception e) {
            logger.warn(e.toString());
            return null;
        }
    }

    /**
     * Method addCommentMetadata ...
     *
     * @param metaDatas of type Collection<FileEntryMetaData>
     * @param comment   of type String
     */
    private static void addCommentMetadata(final Collection<FileEntryMetaData> metaDatas, final String comment) {
        if (StringUtils.isNotEmpty(comment)) {
            final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
            fileEntryMetaData.setKey(new TrackCommentMetaDataKey());
            fileEntryMetaData.setValue(new TrackCommentMetaDataValue(comment));
            metaDatas.add(fileEntryMetaData);
        }
    }

    /**
     * Method addTitleMetadata ...
     *
     * @param metaDatas of type Collection<FileEntryMetaData>
     * @param title     of type String
     */
    private static void addTitleMetadata(final Collection<FileEntryMetaData> metaDatas, final String title) {
        if (StringUtils.isNotEmpty(title)) {
            final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
            fileEntryMetaData.setKey(new TrackTitleMetaDataKey());
            fileEntryMetaData.setValue(new TrackTitleMetaDataValue(title));
            metaDatas.add(fileEntryMetaData);
        }
    }

    /**
     * Method addYearMetadata ...
     *
     * @param metaDatas of type Collection<FileEntryMetaData>
     * @param year      of type String
     */
    private static void addYearMetadata(final Collection<FileEntryMetaData> metaDatas, final String year) {
        if (StringUtils.isNotEmpty(year)) {
            final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
            fileEntryMetaData.setKey(new YearMetaDataKey());
            fileEntryMetaData.setValue(new YearMetaDataValue(year));
            metaDatas.add(fileEntryMetaData);
        }
    }

    /**
     * Method addGenreMetadata ...
     *
     * @param metaDatas of type Collection<FileEntryMetaData>
     * @param genre     of type String
     */
    private static void addGenreMetadata(final Collection<FileEntryMetaData> metaDatas, final String genre) {
        if (StringUtils.isNotEmpty(genre)) {
            final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
            fileEntryMetaData.setKey(new GenreMetaDataKey());
            fileEntryMetaData.setValue(new GenreMetaDataValue(genre));
            metaDatas.add(fileEntryMetaData);
        }
    }

    /**
     * Method addAlbumMetadata ...
     *
     * @param metaDatas of type Collection<FileEntryMetaData>
     * @param album     of type String
     */
    private static void addAlbumMetadata(final Collection<FileEntryMetaData> metaDatas, final String album) {
        if (StringUtils.isNotEmpty(album)) {
            final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
            fileEntryMetaData.setKey(new AlbumMetaDataKey());
            fileEntryMetaData.setValue(new AlbumMetaDataValue(album));
            metaDatas.add(fileEntryMetaData);
        }
    }

    /**
     * Method addArtistMetadata ...
     *
     * @param metaDatas of type Collection<FileEntryMetaData>
     * @param artist    of type String
     */
    private static void addArtistMetadata(final Collection<FileEntryMetaData> metaDatas, final String artist) {
        if (StringUtils.isNotEmpty(artist)) {
            final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
            fileEntryMetaData.setKey(new ArtistMetaDataKey());
            fileEntryMetaData.setValue(new ArtistMetaDataValue(artist));
            metaDatas.add(fileEntryMetaData);
        }
    }
}
