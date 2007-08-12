package directorylister.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * FileEntryComparator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>07/17/2007</pre>
 */
public final class FileEntryComparatorUnitTest {

    /**
     * Field comparator
     */
    private final FileEntryComparator comparator = FileEntryComparator.COMPARATOR;

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {
    }

    /**
     * Method testComparingTwoDirectories1 ...
     */
    @Test()
    public void testComparingTwoDirectories1() {
        final FileEntry fileEntry1 = new FileEntry("abc", FileType.DIRECTORY, 0, null, "abc");
        final FileEntry fileEntry2 = new FileEntry("def", FileType.DIRECTORY, 0, null, "def");
        Assert.assertTrue(0 > comparator.compare(fileEntry1, fileEntry2));
    }

    /**
     * Method testComparingTwoDirectories2 ...
     */
    @Test()
    public void testComparingTwoDirectories2() {
        final FileEntry fileEntry1 = new FileEntry("def", FileType.DIRECTORY, 0, null, "def");
        final FileEntry fileEntry2 = new FileEntry("abc", FileType.DIRECTORY, 0, null, "abc");
        Assert.assertTrue(0 < comparator.compare(fileEntry1, fileEntry2));
    }

    /**
     * Method testComparingTwoFiles1 ...
     */
    @Test()
    public void testComparingTwoFiles1() {
        final FileEntry fileEntry1 = new FileEntry("abc", FileType.FILE, 0, null, "abc");
        final FileEntry fileEntry2 = new FileEntry("def", FileType.FILE, 0, null, "def");
        Assert.assertTrue(0 > comparator.compare(fileEntry1, fileEntry2));
    }

    /**
     * Method testComparingTwoFiles2 ...
     */
    @Test()
    public void testComparingTwoFiles2() {
        final FileEntry fileEntry1 = new FileEntry("def", FileType.FILE, 0, null, "def");
        final FileEntry fileEntry2 = new FileEntry("abc", FileType.FILE, 0, null, "abc");
        Assert.assertTrue(0 < comparator.compare(fileEntry1, fileEntry2));
    }

    /**
     * Method testCompareDirectoryAndFile ...
     */
    @Test()
    public void testCompareDirectoryAndFile() {
        final FileEntry directory = new FileEntry("abc", FileType.DIRECTORY, 0, null, "abc");
        final FileEntry file = new FileEntry("abc", FileType.FILE, 0, null, "abc");
        Assert.assertTrue(0 > comparator.compare(directory, file));
    }

    /**
     * Method testCompareFileAndDirectory ...
     */
    @Test()
    public void testCompareFileAndDirectory() {
        final FileEntry file = new FileEntry("abc", FileType.FILE, 0, null, "abc");
        final FileEntry directory = new FileEntry("abc", FileType.DIRECTORY, 0, null, "abc");
        Assert.assertTrue(0 < comparator.compare(file, directory));
    }
}
