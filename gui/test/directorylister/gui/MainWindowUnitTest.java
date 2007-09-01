package directorylister.gui;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.Window;

/**
 * MainWindow Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>08/17/2007</pre>
 */
public class MainWindowUnitTest {

    /**
     * Field window
     */
    private Window window = null;

    /**
     * Method setUp ...
     *
     * @throws Exception when
     */
    @Before()
    public void setUp() throws Exception {
        final MainWindow mainWindow = new MainWindow();
        window = new Window(mainWindow);
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
     * Method testMainWindowCanBeCreated ...
     *
     * @throws Exception when
     */
    @Test()
    public void testMainWindowCanBeCreated() throws Exception {
        Assert.assertNotNull(window);

        Assert.assertTrue(window.isEnabled().isTrue());
    }
}
