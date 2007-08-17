package directorylister.controllers;

import directorylister.model.JaListerDatabase;
import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JaListerDatabaseController Tester.
 *
 * @author schakal Oleg Atamanenko
 * @since 07/25/2007
 */
public class JaListerDatabaseControllerUnitTest {

    /**
     * Field controller
     */
    private JaListerDatabaseController controller;
    /**
     * Field NEW_ENTRY
     */
    private static final JaListerDatabase NEW_ENTRY = new JaListerDatabase();

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        controller = JaListerDatabaseController.getInstance();
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {

    }

    /**
     * Method testGetInstance ...
     *
     * @throws Exception when
     */
    @org.junit.Test()
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(controller);
    }

    /**
     * Method testSetCurrentFileEntry ...
     *
     * @throws Exception when
     */
    @org.junit.Test()
    public void testSetCurrentFileEntry() throws Exception {

    }

    /**
     * Method testThatListenerCanBeAdded ...
     */
    @Test()
    public void testThatListenerCanBeAdded() {
        final TestJaListerDatabaseListener testFileEntryListener = new TestJaListerDatabaseListener();
        controller.addListener(testFileEntryListener);
        controller.setCurrentJaListerDatabase(NEW_ENTRY);
        Assert.assertTrue(testFileEntryListener.wasCalled);
    }

    /**
     * Method testGetCurrentEntry ...
     *
     * @throws Exception when
     */
    @org.junit.Test()
    public void testGetCurrentEntry() throws Exception {
        controller.setCurrentJaListerDatabase(NEW_ENTRY);

        Assert.assertEquals(NEW_ENTRY, controller.getCurrentDatabase());
    }

    /**
     * Method suite ...
     *
     * @return Test
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(JaListerDatabaseControllerUnitTest.class);
    }

    /**
     * Class TestJaListerDatabaseListener ...
     *
     * @author schakal
     *         Created on 05.08.2007
     */
    private class TestJaListerDatabaseListener extends JaListerDatabaseListenerAdapter {
        /**
         * Field wasCalled
         */
        boolean wasCalled;

        /**
         * {@inheritDoc}
         *
         * @see JaListerDatabaseListenerAdapter#notifyJaListerDatabaseChanged(JaListerDatabase,JaListerDatabase)
         */
        @Override
        public void notifyJaListerDatabaseChanged(final JaListerDatabase currentEntry, final JaListerDatabase newEntry) {
            wasCalled = true;
        }
    }
}
