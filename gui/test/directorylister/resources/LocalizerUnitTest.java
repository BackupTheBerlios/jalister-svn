package directorylister.resources;

import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.Locale;

/**
 * Localizer Tester.
 *
 * @author schakal Oleg Atamanenko
 * @since 07/25/2007
 */
public class LocalizerUnitTest {

    private Localizer localizer;

    @Before()
    public void setUp() throws Exception {
        localizer = new Localizer();
    }

    @After()
    public void tearDown() throws Exception {
        ResourceHandler.getInstance().setLocale(Locale.ENGLISH);
    }

    @Test()
    public void testLocalizationWorkForButton() {
        JButton button = new JButton();
        button.setName("MainWindow");

        localizer.localize(button);

        assertEquals("Directory Lister", button.getText());
    }

    @Test()
    public void testLocalizationWorkForJMenu() {
        JMenu menu = new JMenu();
        menu.setName("MainWindow");
        localizer.localize(menu);
        assertEquals("Directory Lister", menu.getText());
    }

    @Test()
    public void testLocalizationWorkForJFrame() {
        JFrame frame = new JFrame();
        frame.setName("MainWindow");
        localizer.localize(frame);
        assertEquals("Directory Lister", frame.getTitle());
    }

    @Test()
    public void testLocalizationWorkForJMenuItem() {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setName("MainWindow");
        localizer.localize(menuItem);
        assertEquals("Directory Lister", menuItem.getText());
    }

    @Test()
    public void testLocalizationWorkForJLabel() {
        JLabel label = new JLabel();
        label.setName("MainWindow");
        localizer.localize(label);
        assertEquals("Directory Lister", label.getText());
    }

    @Test()
    public void testLocalizationWorkForJDialog() {
        JDialog dialog = new JDialog();
        dialog.setName("MainWindow");
        localizer.localize(dialog);
        assertEquals("Directory Lister", dialog.getTitle());
    }

    @Test()
    public void testLocalizationForNonExistentNameButton() {
        JButton button = new JButton();
        button.setName("Non Existent");

        localizer.localize(button);

        assertEquals("Non Existent", button.getText());
    }

    @Test()
    public void testLocalizationForNullNameButton() {
        JButton button = new JButton();

        localizer.localize(button);

        assertEquals("", button.getText());
    }

    @Test()
    public void testLocalizationWork2() {
        ResourceHandler.getInstance().setLocale(new Locale("RU"));
        JButton button = new JButton();
        button.setName("MainWindow");

        localizer.localize(button);

        assertEquals("Directory Lister RUSSIAN", button.getText());
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(LocalizerUnitTest.class);
    }
}
