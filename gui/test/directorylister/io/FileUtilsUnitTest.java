package directorylister.io;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * FileUtils Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>07/28/2007</pre>
 */
public class FileUtilsUnitTest {

    private File file;

    public FileUtilsUnitTest() {
    }

    @Before()
    public void setUp() throws Exception {

    }

    @After()
    public void tearDown() throws Exception {
        if (null != file) {
            file.delete();
        }
    }

    @Test()
    public void testGetContents() throws Exception {

        file = File.createTempFile("tempFile", "test");
        byte[] bytes = "The Test String".getBytes();
        FileUtils.putContents(file, bytes);

        byte[] contents = FileUtils.getContents(file);

        assertArrayEquals(bytes, contents);
    }

    @Test()
    public void testDeleteFile() throws Exception {
        file = File.createTempFile("tempFile", "test");

        FileUtils.delete(file);

        assertFalse(file.exists());
    }

    @Test()
    public void testDeleteEmptyDirectory() throws Exception {
        file = new File("tempDirectory");
        file.mkdirs();
        FileUtils.delete(file);

        assertFalse(file.exists());
    }

    @Test()
    public void testDeleteNonEmptyDirectory() throws Exception {
        file = new File("rootDirectory");
        file.mkdirs();
        File subFile = new File(file, "file");
        subFile.createNewFile();

        assertTrue(subFile.exists());

        FileUtils.delete(file);

        assertFalse(file.exists());
    }
}
