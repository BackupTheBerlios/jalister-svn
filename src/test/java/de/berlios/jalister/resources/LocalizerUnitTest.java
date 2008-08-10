package de.berlios.jalister.resources;

import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
    public static void tearDown() {
        ResourceHandler.getInstance().setLocale(Locale.ENGLISH);
    }

    /**
     * Method testLocalizationWorkForButton ...
     */
    @Test()
    public void testLocalizationWorkForButton() {
        final JButton button = new JButton();
        button.setName("MainWindow");

        localizer.localize(button);

        assertEquals("JaLister", button.getText());
    }

    /**
     * Method testLocalizationWorkForJMenu ...
     */
    @Test()
    public void testLocalizationWorkForJMenu() {
        final JMenu menu = new JMenu();
        menu.setName("MainWindow");
        localizer.localize(menu);
        assertEquals("JaLister", menu.getText());
    }

    /**
     * Method testLocalizationWorkForJFrame ...
     */
    @Test()
    public void testLocalizationWorkForJFrame() {
        final JFrame frame = new JFrame();
        frame.setName("MainWindow");
        localizer.localize(frame);
        assertEquals("JaLister", frame.getTitle());
    }

    /**
     * Method testLocalizationWorkForJMenuItem ...
     */
    @Test()
    public void testLocalizationWorkForJMenuItem() {
        final JMenuItem menuItem = new JMenuItem();
        menuItem.setName("MainWindow");
        localizer.localize(menuItem);
        assertEquals("JaLister", menuItem.getText());
    }

    /**
     * Method testLocalizationWorkForJLabel ...
     */
    @Test()
    public void testLocalizationWorkForJLabel() {
        final JLabel label = new JLabel();
        label.setName("MainWindow");
        localizer.localize(label);
        assertEquals("JaLister", label.getText());
    }

    /**
     * Method testLocalizationWorkForJDialog ...
     */
    @Test()
    public void testLocalizationWorkForJDialog() {
        final JDialog dialog = new JDialog();
        dialog.setName("MainWindow");
        localizer.localize(dialog);
        assertEquals("JaLister", dialog.getTitle());
    }

    /**
     * Method testLocalizationForNonExistentNameButton ...
     */
    @Test()
    public void testLocalizationForNonExistentNameButton() {
        final JButton button = new JButton();
        button.setName("Non Existent");

        localizer.localize(button);

        assertEquals("Non Existent", button.getText());
    }

    /**
     * Method testLocalizationForNullNameButton ...
     */
    @Test()
    public void testLocalizationForNullNameButton() {
        final JButton button = new JButton();

        localizer.localize(button);

        assertEquals("", button.getText());
    }

    /**
     * Method testLocalizationWork2 ...
     */
    @Test()
    public void testLocalizationWork2() {
        ResourceHandler.getInstance().setLocale(new Locale("RU"));
        final JButton button = new JButton();
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
