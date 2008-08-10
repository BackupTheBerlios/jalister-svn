package de.berlios.jalister.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    @Test()
    public void testGetCreator() throws Exception {
        final FilePathSeparator filePathSeparator = listerDatabase.getCreator();
        Assert.assertNotNull(filePathSeparator);
        Assert.assertEquals(FilePathSeparator.getFilePathSeparator(), filePathSeparator);

    }

    /**
     * Method testSetGetRootPath ...
     *
     * @throws Exception when
     */
    @Test()
    public void testSetGetRootPath() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method testSetGetRootEntry ...
     *
     * @throws Exception when
     */
    @Test()
    public void testSetGetRootEntry() throws Exception {
        //TODO: Test goes here...
    }
}
