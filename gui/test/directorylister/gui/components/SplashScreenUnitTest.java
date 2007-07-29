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

    private Window splashWindow;
    private SplashScreen splashScreen;

    public SplashScreenUnitTest() {
    }

    @Before()
    public void setUp() throws Exception {
        splashScreen = new SplashScreen();

        splashWindow = new Window(splashScreen);
    }

    @After()
    public void tearDown() throws Exception {

    }

    @Test()
    public void testProgressbarExists() throws Exception {
        ProgressBar progressBar = splashWindow.getProgressBar("progressBar");
        Assert.assertTrue(progressBar.isEnabled().isTrue());
    }

    @Test()
    public void testLabelExists() throws Exception {
        TextBox messageLabel = getLabel();
        Assert.assertTrue(messageLabel.isEnabled().isTrue());
    }

    private TextBox getLabel() {
        return splashWindow.getTextBox("messageLabel");
    }

    @Test()
    public void testLabelChangesItsText() throws Exception {
        TextBox label = getLabel();

        splashScreen.notify("New Message");

        Assert.assertEquals("New Message", label.getText());
    }
}
