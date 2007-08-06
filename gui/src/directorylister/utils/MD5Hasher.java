package directorylister.utils;

import directorylister.io.FileUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 13:09:14
 */
public class MD5Hasher {

    /**
     * Do not instantiate MD5Hasher.
     */
    private MD5Hasher() {

    }

    /**
     * Method getMD5 ...
     *
     * @param file of type File
     * @return String
     * @throws IOException when
     */
    public static String getMD5(final File file) throws IOException {
        if (file.isDirectory()) {
            return StringUtils.EMPTY;
        }
        return getMD5(FileUtils.getContents(file));
    }

    /**
     * Method getMD5 ...
     *
     * @param contents of type byte[]
     * @return String
     */
    public static String getMD5(final byte[] contents) {
        return DigestUtils.md5Hex(contents);
    }
}
