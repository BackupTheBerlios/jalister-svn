package directorylister.io;

import java.io.*;
import java.util.logging.Logger;

public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

    public static final int BUFFER = 4096;

    /**
     * Do not instantiate FileUtils.
     */
    private FileUtils() {
    }

    public static byte[] getContents(File file) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        InputStream in = new BufferedInputStream(new FileInputStream(file));

        int c = in.read();

        while (c >= 0) {
            out.write(c);

            c = in.read();
        }

        in.close();
        out.flush();

        return out.toByteArray();
    }

    public static void putContents(File file, byte[] bytes) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));

        os.write(bytes);

        os.flush();
        os.close();
    }

    public static void delete(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                delete(child);
            }
        }

        file.delete();
    }
}
