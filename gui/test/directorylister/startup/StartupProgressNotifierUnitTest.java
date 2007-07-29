package directorylister.startup;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * StartupProgressNotifier Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>07/28/2007</pre>
 */
public class StartupProgressNotifierUnitTest {
    private StartupProgressNotifier notifier;

    public StartupProgressNotifierUnitTest() {
    }

    @Before()
    public void setUp() throws Exception {
        notifier = new StartupProgressNotifier();
    }

    @After()
    public void tearDown() throws Exception {

    }

    @Test()
    public void testAddListener() throws Exception {
        Assert.assertTrue(notifier.addListener(new TestStartupListener()));
    }

    @Test()
    public void testAddListenerTwice() throws Exception {
        StartupListener listener = new TestStartupListener();
        notifier.addListener(listener);
        Assert.assertFalse(notifier.addListener(listener));
    }

    @Test()
    public void testNotifySendsMessages() throws Exception {
        TestStartupListener listener = new TestStartupListener();
        notifier.addListener(listener);

        notifier.notifyListeners("message");

        Assert.assertTrue(listener.notifyReceived);
    }

    private static class TestStartupListener implements StartupListener {
        private boolean notifyReceived;

        public void notify(String message) {
            notifyReceived = true;
        }
    }
}
