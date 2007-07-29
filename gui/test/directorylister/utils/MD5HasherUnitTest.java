package directorylister.utils;

import static directorylister.utils.MD5Hasher.getMD5;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    private File test;
    private File directory;

    public MD5HasherUnitTest() {
    }

    @Before()
    public void setUp() throws Exception {

    }

    @After()
    public void tearDown() throws Exception {
        if (test != null) {
            test.delete();
        }
        if (directory != null) {
            directory.delete();
        }
    }

    @Test()
    public void testGetMD5() throws Exception {
        assertNotNull(getMD5("Test".getBytes()));
    }

    @Test()
    public void testGetMD51() throws Exception {
        test = File.createTempFile("test", "tmp");
        writeContent(test);


        assertNotNull(getMD5(test));
    }

    @Test()
    public void testGetMd5ForDirectoryReturnsEmptyString() throws Exception {
        directory = new File("testDirectory");
        directory.mkdir();
        assertEquals(StringUtils.EMPTY, getMD5(directory));
    }

    private void writeContent(File test) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(test);
        fileOutputStream.write("Test string".getBytes());
        fileOutputStream.close();
    }

}
