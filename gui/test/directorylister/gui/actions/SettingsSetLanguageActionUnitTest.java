package directorylister.gui.actions;

import directorylister.resources.ResourceHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JButton;
import java.util.Locale;

/**
 * SettingsSetLanguageAction Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>07/28/2007</pre>
 */
public class SettingsSetLanguageActionUnitTest {

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
    public static void tearDown() {
        ResourceHandler.getInstance().setLocale(Locale.ENGLISH);
    }

    /**
     * Method testAction ...
     */
    @Test()
    public void testAction() {
        final JButton button = new JButton();
        button.setName("MainMenu.File");
        final SettingsSetLanguageAction action = new SettingsSetLanguageAction(button, new Locale("RU"));
        action.actionPerformed(null);

        Assert.assertEquals("Файл", button.getText());
    }

}
