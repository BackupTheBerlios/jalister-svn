package directorylister.gui.components;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.ProgressBar;
import org.uispec4j.TextBox;
import org.uispec4j.Window;

/**
 * SplashScreen Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>07/28/2007</pre>
 */
public class SplashScreenUnitTest {

    /**
     * Field splashWindow
     */
    private Window splashWindow;
    /**
     * Field splashScreen
     */
    private SplashScreen splashScreen;

    /**
     * Constructs a new SplashScreenUnitTest.
     */
    public SplashScreenUnitTest() {
    }

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        splashScreen = new SplashScreen();

        splashWindow = new Window(splashScreen);
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {

    }

    /**
     * Method testProgressbarExists ...
     *
     * @throws Exception when
     */
    @Test()
    public void testProgressbarExists() throws Exception {
        ProgressBar progressBar = splashWindow.getProgressBar("progressBar");
        Assert.assertTrue(progressBar.isEnabled().isTrue());
    }

    /**
     * Method testLabelExists ...
     *
     * @throws Exception when
     */
    @Test()
    public void testLabelExists() throws Exception {
        TextBox messageLabel = getLabel();
        Assert.assertTrue(messageLabel.isEnabled().isTrue());
    }

    /**
     * Getter for property 'label'.
     *
     * @return Value for property 'label'.
     */
    private TextBox getLabel() {
        return splashWindow.getTextBox("messageLabel");
    }

    /**
     * Method testLabelChangesItsText ...
     *
     * @throws Exception when
     */
    @Test()
    public void testLabelChangesItsText() throws Exception {
        TextBox label = getLabel();

        splashScreen.notify("New Message");

        Assert.assertEquals("New Message", label.getText());
    }
}
