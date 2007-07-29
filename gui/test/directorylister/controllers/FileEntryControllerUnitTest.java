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

    private FileEntryController controller;
    private static final FileEntry NEW_ENTRY = new FileEntry();

    @Before()
    public void setUp() throws Exception {
        controller = FileEntryController.getInstance();
    }

    @After()
    public void tearDown() throws Exception {

    }

    @org.junit.Test()
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(controller);
    }

    @org.junit.Test()
    public void testSetCurrentFileEntry() throws Exception {

    }

    @Test()
    public void testThatListenerCanBeAdded() {
        final TestFileEntryListener testFileEntryListener = new TestFileEntryListener();
        controller.addListener(testFileEntryListener);
        controller.setCurrentFileEntry(NEW_ENTRY);
        Assert.assertTrue(testFileEntryListener.wasCalled);
    }

    @org.junit.Test()
    public void testGetCurrentEntry() throws Exception {
        controller.setCurrentFileEntry(NEW_ENTRY);

        Assert.assertEquals(NEW_ENTRY, controller.getCurrentEntry());
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(FileEntryControllerUnitTest.class);
    }

    private class TestFileEntryListener extends FileEntryListenerAdapter {
        boolean wasCalled;

        @Override
        public void notifyCurrentFileEntryChanged(FileEntry currentEntry, FileEntry newEntry) {
            wasCalled = true;
        }
    }
}
