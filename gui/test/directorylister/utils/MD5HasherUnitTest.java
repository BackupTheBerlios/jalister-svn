package directorylister.utils;

import static directorylister.utils.MD5Hasher.*;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * MD5Hasher Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>07/28/2007</pre>
 */
public class MD5HasherUnitTest {
    /**
     * Field test
     */
    private File test;
    /**
     * Field directory
     */
    private File directory;

    /**
     * Constructs a new MD5HasherUnitTest.
     */
    public MD5HasherUnitTest() {
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
        if (test != null) {
            test.delete();
        }
        if (directory != null) {
            directory.delete();
        }
    }

    /**
     * Method testGetMD5 ...
     *
     * @throws Exception when
     */
    @Test()
    public void testGetMD5() throws Exception {
        assertNotNull(getMD5("Test".getBytes()));
    }

    /**
     * Method testGetMD51 ...
     *
     * @throws Exception when
     */
    @Test()
    public void testGetMD51() throws Exception {
        test = File.createTempFile("test", "tmp");
        writeContent(test);


        assertNotNull(getMD5(test));
    }

    /**
     * Method testGetMd5ForDirectoryReturnsEmptyString ...
     *
     * @throws Exception when
     */
    @Test()
    public void testGetMd5ForDirectoryReturnsEmptyString() throws Exception {
        directory = new File("testDirectory");
        directory.mkdir();
        assertEquals(StringUtils.EMPTY, getMD5(directory));
    }

    /**
     * Method writeContent ...
     *
     * @param test of type File
     * @throws IOException when
     */
    private void writeContent(final File test) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(test);
        fileOutputStream.write("Test string".getBytes());
        fileOutputStream.close();
    }

}
