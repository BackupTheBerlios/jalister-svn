package directorylister.resources;

import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

/**
 * ResourceHandler Tester.
 *
 * @author schakal Oleg Atamanenko
 * @since 07/25/2007
 */
public class ResourceHandlerUnitTest {

    /**
     * Field resourceHandler
     */
    private ResourceHandler resourceHandler;

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        resourceHandler = ResourceHandler.getInstance();
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {
        resourceHandler.setLocale(Locale.ENGLISH);
    }

    /**
     * Method testGetInstance ...
     *
     * @throws Exception when
     */
    @Test()
    public void testGetInstance() throws Exception {
        assertNotNull(resourceHandler);
    }

    /**
     * Method testSetLocale ...
     *
     * @throws Exception when
     */
    @Test()
    public void testSetLocale() throws Exception {
        resourceHandler.setLocale(new Locale("RU"));

        assertEquals("Directory Lister RUSSIAN", resourceHandler.getMessage("MainWindow"));
    }

    /**
     * Method testGetMessage ...
     *
     * @throws Exception when
     */
    @Test()
    public void testGetMessage() throws Exception {
        final String s = resourceHandler.getMessage("MainWindow");
        assertNotNull(s);
        assertEquals("JaLister", s);
    }

    /**
     * Method testKeyIsReturnedForNonExistentValue ...
     */
    @Test()
    public void testKeyIsReturnedForNonExistentValue() {
        assertEquals("The non existent value",
                resourceHandler.getMessage("The non existent value"));
    }

    /**
     * Method suite ...
     *
     * @return Test
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ResourceHandlerUnitTest.class);
    }

}
