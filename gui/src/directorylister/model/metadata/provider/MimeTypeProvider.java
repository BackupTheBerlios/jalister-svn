package directorylister.model.metadata.provider;

import directorylister.utils.SwingUtils;
import directorylister.model.metadata.FileEntryMetaData;
import directorylister.model.metadata.key.MimeTypeMetaDataKey;
import directorylister.model.metadata.value.MimeTypeMetaDataValue;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(MimeTypeProvider.class);
    /**
     * Field BUFFER_SIZE
     */
    private static final int BUFFER_SIZE = 262144; //256 KB.

    public Collection<FileEntryMetaData> getMetadata(final File file) {
        String mimeType;
        if (file.isDirectory()) {
            mimeType = "Directory";
        }
        else {
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
        String mimeType = null;
        if (1 == 1) {
            return mimeType;
        }
        try {
            final byte[] content = readFirstBytes(file);
            final MagicMatch magicMatch = Magic.getMagicMatch(content);
            mimeType = magicMatch.getMimeType();
        }
        catch(MagicParseException e) {
            logger.warn(e);
        }
        catch(MagicMatchNotFoundException e) {
            logger.warn(e);
        }
        catch(MagicException e) {
            logger.warn(e);
        }
        return mimeType;
    }

    private static byte[] readFirstBytes(final File file) {
        final long fileSize = file.length();
        final int bufferSize = (int) (fileSize > BUFFER_SIZE ? BUFFER_SIZE : fileSize);
        final byte[] buffer = new byte[bufferSize];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            inputStream.read(buffer);
        }
        catch(FileNotFoundException e) {
            SwingUtils.showError(e.getMessage());
            logger.error(e);
        }
        catch(IOException e) {
            SwingUtils.showError(e.getMessage());
            logger.error(e);
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch(IOException e) {
                    SwingUtils.showError(e.getMessage());
                    logger.error(e);
                }
            }
        }
        return buffer;
    }

    private static String getMimeTypeUsingJAF(final File file) {
        return FileTypeMap.getDefaultFileTypeMap().getContentType(file);
    }
}
