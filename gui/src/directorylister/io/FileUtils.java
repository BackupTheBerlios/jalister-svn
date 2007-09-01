package directorylister.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class FileUtils ...
 *
 * @author schakal
 *         Created on 05.08.2007
 */
public class FileUtils {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(FileUtils.class.getName());

    /**
     * Field BUFFER
     */
    public static final int BUFFER = 4096;

    /**
     * Do not instantiate FileUtils.
     */
    private FileUtils() {
    }

    /**
     * Method getContents ...
     *
     * @param file of type File
     * @return byte[]
     * @throws IOException when
     */
    public static byte[] getContents(final File file) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final InputStream in = new BufferedInputStream(new FileInputStream(file));

        int c = in.read();

        while (c >= 0) {
            out.write(c);

            c = in.read();
        }

        in.close();
        out.flush();

        return out.toByteArray();
    }

    /**
     * Method putContents ...
     *
     * @param file  of type File
     * @param bytes of type byte[]
     * @throws IOException when
     */
    public static void putContents(final File file, final byte[] bytes) throws IOException {
        final OutputStream os = new BufferedOutputStream(new FileOutputStream(file));

        os.write(bytes);

        os.flush();
        os.close();
    }

    /**
     * Method delete ...
     *
     * @param file of type File
     */
    public static void delete(final File file) {
        if (file.isDirectory()) {
            for (final File child : file.listFiles()) {
                delete(child);
            }
        }

        file.delete();
    }

    /**
     * Checks if given file is Unix symbolic link.
     *
     * @param pathname file to check.
     * @return <code>true</code> if pathname is link. <code>false</code> otherwise.
     */
    public static boolean isLink(final File pathname) {
        try {
            return !pathname.getCanonicalPath().equals(pathname.getAbsolutePath());
        }
        catch(IOException e) {
            logger.error(e);
            return false;
        }
    }
}
