package directorylister.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * JaListerDatabase Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>08/17/2007</pre>
 */
public class JaListerDatabaseUnitTest {

    /**
     * Field listerDatabase
     */
    private JaListerDatabase listerDatabase;

    /**
     * Constructs a new JaListerDatabaseUnitTest.
     */
    public JaListerDatabaseUnitTest() {
    }

    /**
     * Method setUp ...
     *
     * @throws Exception when
     */
    @Before()
    public void setUp() throws Exception {
        listerDatabase = new JaListerDatabase();
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
     * Method testGetCreator ...
     *
     * @throws Exception when
     */
    @org.junit.Test()
    public void testGetCreator() throws Exception {
        OperatingSystem operatingSystem = listerDatabase.getCreator();
        Assert.assertNotNull(operatingSystem);
        Assert.assertEquals(OperatingSystem.getOperatingSystem(), operatingSystem);

    }

    /**
     * Method testSetGetRootPath ...
     *
     * @throws Exception when
     */
    @org.junit.Test()
    public void testSetGetRootPath() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method testSetGetRootEntry ...
     *
     * @throws Exception when
     */
    @org.junit.Test()
    public void testSetGetRootEntry() throws Exception {
        //TODO: Test goes here...
    }
}
