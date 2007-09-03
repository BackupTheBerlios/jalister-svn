package directorylister.model;

import directorylister.model.metadata.FileEntryMetaData;
import directorylister.model.transformers.SortedFileEntryListTransformer;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:14:03
 */
public class FileEntryUnitTest {
    /**
     * Field fileEntry
     */
    private FileEntry fileEntry;

    /**
     * Field context
     */
    private Mockery context;

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        context = new Mockery();
        fileEntry = new FileEntry("the name.txt", FileType.DIRECTORY, "", null);
    }

    /**
     * Method testThatFileNameCanBeGetted ...
     */
    @Test()
    public void testThatFileNameCanBeGetted() {
        fileEntry = new FileEntry("text.txt", "", null);
        Assert.assertNotNull("Filename is null", fileEntry.getFileName());

        assertEquals("Filename is wrong", "text.txt", fileEntry.getFileName());
    }

    /**
     * Method testThatByDefaultCreatedFile ...
     */
    @Test()
    public void testThatByDefaultCreatedFile() {
        fileEntry = new FileEntry("the name.txt", "", null);
        assertEquals(FileType.FILE, fileEntry.getFileType());
    }

    /**
     * Method testThatDirectoryCanBeCreated ...
     */
    @Test()
    public void testThatDirectoryCanBeCreated() {
        fileEntry = new FileEntry("the name.txt", FileType.DIRECTORY, "", null);
        assertEquals(FileType.DIRECTORY, fileEntry.getFileType());
    }

    /**
     * Method testToString ...
     */
    @Test()
    public void testToString() {
        assertNotNull(null, fileEntry.toString());
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {

    }

    /**
     * Method testGetMetaDatas ...
     */
    @Test()
    public void testGetMetaDatas() {
        final Collection<FileEntryMetaData> metaDatas = fileEntry.getMetadatas();
        assertNotNull(metaDatas);
        assertTrue(metaDatas.isEmpty());
    }

    /**
     * Method testAddMetadata ...
     */
    @Test()
    public void testAddMetadata() {
        final FileEntryMetaData data = new FileEntryMetaData();
        fileEntry.addMetaData(data);
        final Collection<FileEntryMetaData> datas = fileEntry.getMetadatas();
        assertTrue(datas.contains(data));
    }

    /**
     * Method testSetMetaData ...
     */
    @Test()
    public void testSetMetaData() {
        final Set<FileEntryMetaData> metaDatas = new HashSet<FileEntryMetaData>();
        fileEntry.setMetadatas(metaDatas);
        final Collection<FileEntryMetaData> datas = fileEntry.getMetadatas();
        assertSame(metaDatas, datas);
    }

    /**
     * Method testGetChildsWithTransformer ...
     */
    @Test()
    public void testGetChildsWithTransformer() {
        final FileEntry def = new FileEntry("def", null, "def");
        final FileEntry abc = new FileEntry("abc", null, "abc");

        fileEntry.addChild(def);
        fileEntry.addChild(abc);

        final List<FileEntry> childs = fileEntry.getChilds(SortedFileEntryListTransformer.TRANSFORMER);
        assertNotNull(childs);
        assertEquals(2, childs.size());

        assertEquals(abc, childs.get(0));
        assertEquals(def, childs.get(1));
    }

    /**
     * Method testAcceptVisitor ...
     *
     * @throws Exception when
     */
    @Test()
    public void testAcceptVisitor() throws Exception {
        fileEntry = new FileEntry();
        fileEntry.setFileName("root");

        final FileEntryVisitor mockVisitor = context.mock(FileEntryVisitor.class);

        context.checking(new Expectations() {
            {
                one(mockVisitor).acceptEntry(fileEntry);
            }
        });

        fileEntry.acceptVisitor(mockVisitor);
        context.assertIsSatisfied();
    }

    /**
     * Method testAcceptVisitorWithChilds ...
     *
     * @throws Exception when
     */
    @Test()
    public void testAcceptVisitorWithChilds() throws Exception {
        fileEntry = new FileEntry();
        fileEntry.setFileName("root");

        final FileEntry child1 = new FileEntry();
        child1.setFileName("child1");
        final FileEntry child2 = new FileEntry();
        child2.setFileName("child2");

        fileEntry.addChild(child1);
        fileEntry.addChild(child2);

        final FileEntryVisitor mockVisitor = context.mock(FileEntryVisitor.class);

        context.checking(new Expectations() {
            {
                one(mockVisitor).acceptEntry(fileEntry);
                one(mockVisitor).levelStarted(fileEntry);
                one(mockVisitor).acceptEntry(child1);
                one(mockVisitor).acceptEntry(child2);
                one(mockVisitor).levelEnded(fileEntry);
            }
        });

        fileEntry.acceptVisitor(mockVisitor);
        context.assertIsSatisfied();
    }
}
