package directorylister.model;

import directorylister.model.transformers.SortedFileEntryListTransformer;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:14:03
 */
public class FileEntryUnitTest {
    private FileEntry fileEntry;

    @Before()
    public void setUp() {
        fileEntry = new FileEntry("the name.txt", true, new Date().getTime(), "", null);
    }

    @Test()
    public void testThatFileNameCanBeGetted() {
        fileEntry = new FileEntry("text.txt", new Date().getTime(), "", null);
        Assert.assertNotNull("Filename is null", fileEntry.getFileName());

        assertEquals("Filename is wrong", "text.txt", fileEntry.getFileName());
    }

    @Test()
    public void testThatByDefaultCreatedFile() {
        fileEntry = new FileEntry("the name.txt", new Date().getTime(), "", null);
        assertEquals(false, fileEntry.isDirectory());
    }

    @Test()
    public void testThatDirectoryCanBeCreated() {
        fileEntry = new FileEntry("the name.txt", true, new Date().getTime(), "", null);
        assertEquals(true, fileEntry.isDirectory());
    }

    @Test()
    public void testThatDateCanBeGetted() {
        final long modified = new Date().getTime();
        fileEntry = new FileEntry("the name.txt", modified, "", null);
        assertEquals(modified, fileEntry.getLastModified());
    }

    @Test()
    public void testToString() {
        assertNotNull(null, fileEntry.toString());
    }

    @After()
    public void tearDown() {

    }

    @Test()
    public void testGetMetaDatas() {
        final Set<FileEntryMetaData> metaDatas = fileEntry.getMetadatas();
        assertNotNull(metaDatas);
        assertTrue(metaDatas.isEmpty());
    }

    @Test()
    public void testAddMetadata() {
        final FileEntryMetaData data = new FileEntryMetaData();
        fileEntry.addMetaData(data);
        final Set<FileEntryMetaData> datas = fileEntry.getMetadatas();
        assertTrue(datas.contains(data));
    }

    @Test()
    public void testSetMetaData() {
        final Set<FileEntryMetaData> metaDatas = new HashSet<FileEntryMetaData>();
        fileEntry.setMetadatas(metaDatas);
        final Set<FileEntryMetaData> datas = fileEntry.getMetadatas();
        assertSame(metaDatas, datas);
    }

    @Test()
    public void testGetChildsWithTransformer() {
        final FileEntry def = new FileEntry("def", 0, null, "def");
        final FileEntry abc = new FileEntry("abc", 0, null, "abc");

        fileEntry.addChild(def);
        fileEntry.addChild(abc);

        final List<FileEntry> childs = fileEntry.getChilds(SortedFileEntryListTransformer.TRANSFORMER);
        assertNotNull(childs);
        assertEquals(2, childs.size());

        assertEquals(abc, childs.get(0));
        assertEquals(def, childs.get(1));
    }
}
