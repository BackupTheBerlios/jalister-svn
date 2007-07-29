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

    private ResourceHandler resourceHandler;

    @Before()
    public void setUp() throws Exception {
        resourceHandler = ResourceHandler.getInstance();
    }

    @After()
    public void tearDown() throws Exception {
        resourceHandler.setLocale(Locale.ENGLISH);
    }

    @Test()
    public void testGetInstance() throws Exception {
        assertNotNull(resourceHandler);
    }

    @Test()
    public void testSetLocale() throws Exception {
        resourceHandler.setLocale(new Locale("RU"));

        assertEquals("Directory Lister RUSSIAN", resourceHandler.getMessage("MainWindow"));
    }

    @org.junit.Test()
    public void testGetMessage() throws Exception {
        final String s = resourceHandler.getMessage("MainWindow");
        assertNotNull(s);
        assertEquals("Directory Lister", s);
    }

    @Test()
    public void testKeyIsReturnedForNonExistentValue() {
        assertEquals("The non existent value",
                resourceHandler.getMessage("The non existent value"));
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ResourceHandlerUnitTest.class);
    }

}
