package directorylister.gui.actions;

import directorylister.resources.ResourceHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.Locale;

/**
 * SettingsSetLanguageAction Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>07/28/2007</pre>
 */
public class SettingsSetLanguageActionUnitTest {

    private SettingsSetLanguageAction action;

    public SettingsSetLanguageActionUnitTest() {

    }

    @Before()
    public void setUp() throws Exception {

    }

    @After()
    public void tearDown() throws Exception {
        ResourceHandler.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test()
    public void testAction() {
        JButton button = new JButton();
        button.setName("MainMenu.File");
        action = new SettingsSetLanguageAction(button, new Locale("RU"));
        action.actionPerformed(null);

        Assert.assertEquals("Файл", button.getText());
    }

}
