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
public class FileEntryComparatorUnitTest {

    private FileEntryComparator comparator = FileEntryComparator.COMPARATOR;

    @Before()
    public void setUp() throws Exception {
    }

    @After()
    public void tearDown() throws Exception {
    }

    @Test()
    public void testComparingTwoDirectories1() {
        FileEntry fileEntry1 = new FileEntry("abc", true, 0, null, "abc");
        FileEntry fileEntry2 = new FileEntry("def", true, 0, null, "def");
        Assert.assertTrue(0 > comparator.compare(fileEntry1, fileEntry2));
    }

    @Test()
    public void testComparingTwoDirectories2() {
        FileEntry fileEntry1 = new FileEntry("def", true, 0, null, "def");
        FileEntry fileEntry2 = new FileEntry("abc", true, 0, null, "abc");
        Assert.assertTrue(0 < comparator.compare(fileEntry1, fileEntry2));
    }

    @Test()
    public void testComparingTwoFiles1() {
        FileEntry fileEntry1 = new FileEntry("abc", false, 0, null, "abc");
        FileEntry fileEntry2 = new FileEntry("def", false, 0, null, "def");
        Assert.assertTrue(0 > comparator.compare(fileEntry1, fileEntry2));
    }

    @Test()
    public void testComparingTwoFiles2() {
        FileEntry fileEntry1 = new FileEntry("def", false, 0, null, "def");
        FileEntry fileEntry2 = new FileEntry("abc", false, 0, null, "abc");
        Assert.assertTrue(0 < comparator.compare(fileEntry1, fileEntry2));
    }

    @Test()
    public void testCompareDirectoryAndFile() {
        FileEntry directory = new FileEntry("abc", true, 0, null, "abc");
        FileEntry file = new FileEntry("abc", false, 0, null, "abc");
        Assert.assertTrue(0 > comparator.compare(directory, file));
    }

    @Test()
    public void testCompareFileAndDirectory() {
        FileEntry file = new FileEntry("abc", false, 0, null, "abc");
        FileEntry directory = new FileEntry("abc", true, 0, null, "abc");
        Assert.assertTrue(0 < comparator.compare(file, directory));
    }
}
