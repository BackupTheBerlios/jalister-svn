package directorylister.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * ObjectUtils Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>07/28/2007</pre>
 */
public class ObjectUtilsUnitTest {
    /**
     * Constructs a new ObjectUtilsUnitTest.
     */
    public ObjectUtilsUnitTest() {
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

    }

    /**
     * Method testToString ...
     *
     * @throws Exception when
     */
    @org.junit.Test()
    public void testToString() throws Exception {
        Assert.assertNotNull(ObjectUtils.buildToString(this));
    }
}
