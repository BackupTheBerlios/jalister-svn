package directorylister.notification;

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
    /**
     * Field notifier
     */
    private ProgressNotifier notifier;

    /**
     * Constructs a new StartupProgressNotifierUnitTest.
     */
    public StartupProgressNotifierUnitTest() {
    }

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        notifier = new ProgressNotifier();
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {

    }

    /**
     * Method testAddListener ...
     *
     * @throws Exception when
     */
    @Test()
    public void testAddListener() throws Exception {
        Assert.assertTrue(notifier.addListener(new TestStartupListener()));
    }

    /**
     * Method testAddListenerTwice ...
     *
     * @throws Exception when
     */
    @Test()
    public void testAddListenerTwice() throws Exception {
        final ProgressListener listener = new TestStartupListener();
        notifier.addListener(listener);
        Assert.assertFalse(notifier.addListener(listener));
    }

    /**
     * Method testNotifySendsMessages ...
     *
     * @throws Exception when
     */
    @Test()
    public void testNotifySendsMessages() throws Exception {
        final TestStartupListener listener = new TestStartupListener();
        notifier.addListener(listener);

        notifier.notifyListeners("message");

        Assert.assertTrue(listener.notifyReceived);
    }

    /**
     * Class TestStartupListener ...
     *
     * @author schakal
     *         Created on 05.08.2007
     */
    private static class TestStartupListener implements ProgressListener {
        /**
         * Field notifyReceived
         */
        private boolean notifyReceived;

        /**
         * {@inheritDoc}
         *
         * @see ProgressListener#notify(Notification)
         */
        public void notify(final Notification message) {
            notifyReceived = true;
        }
    }
}
