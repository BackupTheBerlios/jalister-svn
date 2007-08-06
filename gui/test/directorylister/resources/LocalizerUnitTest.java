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

    /**
     * Field localizer
     */
    private Localizer localizer;

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        localizer = new Localizer();
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {
        ResourceHandler.getInstance().setLocale(Locale.ENGLISH);
    }

    /**
     * Method testLocalizationWorkForButton ...
     */
    @Test()
    public void testLocalizationWorkForButton() {
        JButton button = new JButton();
        button.setName("MainWindow");

        localizer.localize(button);

        assertEquals("Directory Lister", button.getText());
    }

    /**
     * Method testLocalizationWorkForJMenu ...
     */
    @Test()
    public void testLocalizationWorkForJMenu() {
        JMenu menu = new JMenu();
        menu.setName("MainWindow");
        localizer.localize(menu);
        assertEquals("Directory Lister", menu.getText());
    }

    /**
     * Method testLocalizationWorkForJFrame ...
     */
    @Test()
    public void testLocalizationWorkForJFrame() {
        JFrame frame = new JFrame();
        frame.setName("MainWindow");
        localizer.localize(frame);
        assertEquals("Directory Lister", frame.getTitle());
    }

    /**
     * Method testLocalizationWorkForJMenuItem ...
     */
    @Test()
    public void testLocalizationWorkForJMenuItem() {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setName("MainWindow");
        localizer.localize(menuItem);
        assertEquals("Directory Lister", menuItem.getText());
    }

    /**
     * Method testLocalizationWorkForJLabel ...
     */
    @Test()
    public void testLocalizationWorkForJLabel() {
        JLabel label = new JLabel();
        label.setName("MainWindow");
        localizer.localize(label);
        assertEquals("Directory Lister", label.getText());
    }

    /**
     * Method testLocalizationWorkForJDialog ...
     */
    @Test()
    public void testLocalizationWorkForJDialog() {
        JDialog dialog = new JDialog();
        dialog.setName("MainWindow");
        localizer.localize(dialog);
        assertEquals("Directory Lister", dialog.getTitle());
    }

    /**
     * Method testLocalizationForNonExistentNameButton ...
     */
    @Test()
    public void testLocalizationForNonExistentNameButton() {
        JButton button = new JButton();
        button.setName("Non Existent");

        localizer.localize(button);

        assertEquals("Non Existent", button.getText());
    }

    /**
     * Method testLocalizationForNullNameButton ...
     */
    @Test()
    public void testLocalizationForNullNameButton() {
        JButton button = new JButton();

        localizer.localize(button);

        assertEquals("", button.getText());
    }

    /**
     * Method testLocalizationWork2 ...
     */
    @Test()
    public void testLocalizationWork2() {
        ResourceHandler.getInstance().setLocale(new Locale("RU"));
        JButton button = new JButton();
        button.setName("MainWindow");

        localizer.localize(button);

        assertEquals("Directory Lister RUSSIAN", button.getText());
    }

    /**
     * Method suite ...
     *
     * @return Test
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(LocalizerUnitTest.class);
    }
}
