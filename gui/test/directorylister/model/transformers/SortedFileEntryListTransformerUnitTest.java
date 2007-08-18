package directorylister.model.transformers;

import directorylister.model.FileEntry;
import directorylister.model.FileType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 25.07.2007 0:21:16
 */
public class SortedFileEntryListTransformerUnitTest {
    /**
     * Field transformer
     */
    private SortedFileEntryListTransformer transformer;

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        transformer = SortedFileEntryListTransformer.TRANSFORMER;
    }

    /**
     * Method testTransformerIsNotNull ...
     */
    @Test()
    public void testTransformerIsNotNull() {
        Assert.assertNotNull(transformer);
    }

    /**
     * Method testTransformation ...
     */
    @Test()
    public void testTransformation() {
        final List<FileEntry> entries = new ArrayList<FileEntry>();
        final FileEntry entry1 = new FileEntry("def", FileType.FILE, null, "def");
        final FileEntry entry2 = new FileEntry("abc", FileType.FILE, null, "abc");
        entries.add(entry1);
        entries.add(entry2);
        final List<FileEntry> sorted = transformer.transform(entries);
        Assert.assertNotSame(entries, sorted);
        Assert.assertEquals(entries.size(), sorted.size());
        Assert.assertTrue(sorted.contains(entry1));
        Assert.assertTrue(sorted.contains(entry2));
        Assert.assertTrue(sorted.get(0).equals(entry2));
        Assert.assertTrue(sorted.get(1).equals(entry1));
    }

    /**
     * Method testInterfaceIsImplemented ...
     */
    @Test()
    public void testInterfaceIsImplemented() {
        final Class<?>[] interfaces = SortedFileEntryListTransformer.class.getInterfaces();
        for (final Class<?> anInterface : interfaces) {
            if (anInterface.equals(Transformer.class)) {
                return;
            }
        }
        Assert.fail("Interface Transformer is not implemented");
    }
}
