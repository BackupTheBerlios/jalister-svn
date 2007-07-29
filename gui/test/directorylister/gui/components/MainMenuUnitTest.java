package directorylister.gui.components;

import directorylister.resources.Localizer;
import directorylister.resources.ResourceHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.MenuBar;
import org.uispec4j.MenuItem;

import java.util.Locale;

/**
 * MainMenu Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>07/28/2007</pre>
 */
public class MainMenuUnitTest {

    private MainMenu mainMenu;
    private MenuBar menuBar;
    private ResourceHandler resourceHandler;

    public MainMenuUnitTest() {
    }

    @Before()
    public void setUp() throws Exception {
        mainMenu = new MainMenu(null);

        resourceHandler = ResourceHandler.getInstance();
        resourceHandler.setLocale(Locale.ENGLISH);
        new Localizer().localize(mainMenu);

        menuBar = new MenuBar(mainMenu);
    }

    @After()
    public void tearDown() throws Exception {

    }

    @Test()
    public void testMenuIsEnabled() throws Exception {
        Assert.assertTrue(menuBar.isEnabled().isTrue());
    }

    @Test()
    public void testFileItemsAreAdded() throws Exception {
        MenuItem file = menuBar.getMenu(resourceHandler.getMessage("MainMenu.File"));
        Assert.assertTrue(file.isEnabled().isTrue());
    }
}
