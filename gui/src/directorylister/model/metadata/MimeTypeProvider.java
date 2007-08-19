package directorylister.model.metadata;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author schakal Oleg Atamanenko
 * @since 19.08.2007 12:14:35
 */
// TODO: Improve capabilities of the code.
public class MimeTypeProvider implements MetadataProvider {

    private static final Log logger = LogFactory.getLog(MimeTypeProvider.class);
    private static final int BUFFER_SIZE = 2 << 16;

    public MimeTypeProvider() {

    }

    public Collection<FileEntryMetaData> getMetadata(final File file) {
        String mimeType;
        if (file.isDirectory()) {
            mimeType = "Directory";
        } else {

            mimeType = getMimeTypeUsingJMimeMagic(file);

            if (null == mimeType) {
                mimeType = getMimeTypeUsingJAF(file);
            }
        }

        final FileEntryMetaData fileEntryMetaData = new FileEntryMetaData();
        fileEntryMetaData.setKey(new MimeTypeMetaDataKey());
        fileEntryMetaData.setValue(new MimeTypeMetaDataValue(mimeType));
        return Arrays.asList(fileEntryMetaData);
    }

    private String getMimeTypeUsingJMimeMagic(final File file) {
        // TODO: 
        if(true){
            return null;
        }
        String mimeType = null;
        try {
            final byte[] content = readFirstBytes(file);
            final MagicMatch magicMatch = Magic.getMagicMatch(content);
            mimeType = magicMatch.getMimeType();
        } catch (MagicParseException e) {
            logger.warn(e);
        } catch (MagicMatchNotFoundException e) {
            logger.warn(e);
        } catch (MagicException e) {
            logger.warn(e);
        }
        return mimeType;
    }

    private byte[] readFirstBytes(final File file) {
        final long fileSize = file.length();
        final int bufferSize = (int) (fileSize > BUFFER_SIZE ? BUFFER_SIZE : fileSize);
        final byte[] buffer = new byte[bufferSize];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            inputStream.read(buffer);
        }
        catch (IOException e) {
            logger.error(e);
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        return buffer;
    }

    private String getMimeTypeUsingJAF(final File file) {
        return MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file);
    }
}
