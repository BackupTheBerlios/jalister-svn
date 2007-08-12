package directorylister.controllers;

import directorylister.model.FileEntry;
import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * FileEntryController Tester.
 *
 * @author schakal Oleg Atamanenko
 * @since 07/25/2007
 */
public class FileEntryControllerUnitTest {

    /**
     * Field controller
     */
    private FileEntryController controller;
    /**
     * Field NEW_ENTRY
     */
    private static final FileEntry NEW_ENTRY = new FileEntry();

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        controller = FileEntryController.getInstance();
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
        final TestFileEntryListener testFileEntryListener = new TestFileEntryListener();
        controller.addListener(testFileEntryListener);
        controller.setCurrentFileEntry(NEW_ENTRY);
        Assert.assertTrue(testFileEntryListener.wasCalled);
    }

    /**
     * Method testGetCurrentEntry ...
     *
     * @throws Exception when
     */
    @org.junit.Test()
    public void testGetCurrentEntry() throws Exception {
        controller.setCurrentFileEntry(NEW_ENTRY);

        Assert.assertEquals(NEW_ENTRY, controller.getCurrentEntry());
    }

    /**
     * Method suite ...
     *
     * @return Test
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(FileEntryControllerUnitTest.class);
    }

    /**
     * Class TestFileEntryListener ...
     *
     * @author schakal
     *         Created on 05.08.2007
     */
    private class TestFileEntryListener extends FileEntryListenerAdapter {
        /**
         * Field wasCalled
         */
        boolean wasCalled;

        /**
         * {@inheritDoc}
         *
         * @see FileEntryListenerAdapter#notifyCurrentFileEntryChanged(FileEntry,FileEntry)
         */
        @Override
        public void notifyCurrentFileEntryChanged(final FileEntry currentEntry, final FileEntry newEntry) {
            wasCalled = true;
        }
    }
}
