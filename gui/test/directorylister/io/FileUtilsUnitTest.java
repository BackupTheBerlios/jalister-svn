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

    /**
     * Field file
     */
    private File file;

    /**
     * Constructs a new FileUtilsUnitTest.
     */
    public FileUtilsUnitTest() {
    }

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {

    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {
        if (null != file) {
            file.delete();
        }
    }

    /**
     * Method testGetContents ...
     *
     * @throws Exception when
     */
    @Test()
    public void testGetContents() throws Exception {

        file = File.createTempFile("tempFile", "test");
        final byte[] bytes = "The Test String".getBytes();
        FileUtils.putContents(file, bytes);

        final byte[] contents = FileUtils.getContents(file);

        assertArrayEquals(bytes, contents);
    }

    /**
     * Method testDeleteFile ...
     *
     * @throws Exception when
     */
    @Test()
    public void testDeleteFile() throws Exception {
        file = File.createTempFile("tempFile", "test");

        FileUtils.delete(file);

        assertFalse(file.exists());
    }

    /**
     * Method testDeleteEmptyDirectory ...
     *
     * @throws Exception when
     */
    @Test()
    public void testDeleteEmptyDirectory() throws Exception {
        file = new File("tempDirectory");
        file.mkdirs();
        FileUtils.delete(file);

        assertFalse(file.exists());
    }

    /**
     * Method testDeleteNonEmptyDirectory ...
     *
     * @throws Exception when
     */
    @Test()
    public void testDeleteNonEmptyDirectory() throws Exception {
        file = new File("rootDirectory");
        file.mkdirs();
        final File subFile = new File(file, "file");
        subFile.createNewFile();

        assertTrue(subFile.exists());

        FileUtils.delete(file);

        assertFalse(file.exists());
    }
}
