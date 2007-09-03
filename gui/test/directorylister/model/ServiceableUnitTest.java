package directorylister.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ServiceableImpl Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>08/17/2007</pre>
 */
public class ServiceableUnitTest {

    /**
     * Field serviceable
     */
    private Serviceable serviceable;
    /**
     * Field service
     */
    private Service service;

    /**
     * Method setUp ...
     *
     * @throws Exception when
     */
    @Before()
    public void setUp() throws Exception {
        serviceable = new ServiceableImpl();
        service = new TestDefaultServiceImpl();
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
     * Method testAttachService ...
     *
     * @throws Exception when
     */
    @Test()
    public void testAttachService() throws Exception {
        Assert.assertTrue(serviceable.attachService(service));
    }

    /**
     * Method testAttachServiceTwice ...
     *
     * @throws Exception when
     */
    @Test()
    public void testAttachServiceTwice() throws Exception {
        serviceable.attachService(service);
        Assert.assertFalse(serviceable.attachService(service));
    }

    /**
     * Method testDetachUnattachedService ...
     *
     * @throws Exception when
     */
    @Test()
    public void testDetachUnattachedService() throws Exception {
        Assert.assertFalse(serviceable.detachService(service));
    }

    /**
     * Method testDetachService ...
     *
     * @throws Exception when
     */
    @Test()
    public void testDetachService() throws Exception {
        serviceable.attachService(service);
        Assert.assertTrue(serviceable.detachService(service));
    }

    /**
     * Method testDetachServiceTwice ...
     *
     * @throws Exception when
     */
    @Test()
    public void testDetachServiceTwice() throws Exception {
        serviceable.attachService(service);
        serviceable.detachService(service);
        Assert.assertFalse(serviceable.detachService(service));
    }

    /**
     * Class TestDefaultServiceImpl ...
     *
     * @author schakal
     *         Created on 17.08.2007
     */
    private static class TestDefaultServiceImpl extends DefaultServiceImpl {
    }
}
