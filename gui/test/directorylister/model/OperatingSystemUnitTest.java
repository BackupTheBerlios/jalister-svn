package directorylister.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * OperatingSystem Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>08/17/2007</pre>
 */
public class OperatingSystemUnitTest {
    /**
     * Constructs a new OperatingSystemUnitTest.
     */
    public OperatingSystemUnitTest() {
    }

    /**
     * Method setUp ...
     *
     * @throws Exception when
     */
    @Before()
    public void setUp() throws Exception {

    }

    /**
     * Method tearDown ...
     *
     * @throws Exception when
     */
    @After()
    public void tearDown() throws Exception {

    }

    /**
     * Method testGetOperatingSystem ...
     *
     * @throws Exception when
     */
    @Test()
    public void testGetOperatingSystem() throws Exception {
        final OperatingSystem operatingSystem = OperatingSystem.getOperatingSystem();
        Assert.assertNotNull(operatingSystem);

        Assert.assertTrue(operatingSystem != OperatingSystem.UNKNOWN);
    }

    /**
     * Method testGetFileSeparator ...
     *
     * @throws Exception when
     */
    @Test()
    public void testGetFileSeparator() throws Exception {
        Assert.assertEquals(File.separator, OperatingSystem.getOperatingSystem().getFileSeparator());
    }
}