package directorylister.search;

import directorylister.model.FileEntry;
import directorylister.model.FileType;
import directorylister.model.JaListerDatabase;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Searcher Tester.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since <pre>08/05/2007</pre>
 */
public class SearcherUnitTest {

    /**
     * Field searcher
     */
    private Searcher searcher;

    /**
     * Method setUpClass ...
     */
    @BeforeClass
    public static void setUpClass() {
        BasicConfigurator.configure();
    }

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        searcher = new Searcher();
    }

    /**
     * Method testSearchSimple ...
     *
     * @throws Exception when
     */
    @Test()
    public void testSearchSimple() throws Exception {
        final JaListerDatabase database = new JaListerDatabase();
        final FileEntry root = new FileEntry();
        root.setFileName("root");
        root.setShortName("root");
        database.setRootEntry(root);

        database.attachService(searcher);

        final SearchResult searchResult = searcher.search("oo");
        Assert.assertEquals(root, searchResult.getRoot());
    }

    /**
     * Method testSearchWithChilds ...
     *
     * @throws Exception when
     */
    @Test()
    public void testSearchWithChilds() throws Exception {
        final JaListerDatabase database = new JaListerDatabase();
        final FileEntry entry = new FileEntry();
        entry.setFileName("root");
        entry.setShortName("root");
        entry.setFileType(FileType.DIRECTORY);

        final FileEntry child1 = new FileEntry();
        child1.setFileName("root/baz");
        child1.setShortName("baz");
        child1.setFileType(FileType.FILE);
        entry.addChild(child1);


        final FileEntry child2 = new FileEntry();
        child2.setFileName("root/bar");
        child2.setShortName("bar");
        child2.setFileType(FileType.FILE);
        entry.addChild(child2);

        database.setRootEntry(entry);

        database.attachService(searcher);

        final SearchResult searchResult = searcher.search("ba");
        Assert.assertEquals(entry, searchResult.getRoot());
        Assert.assertEquals(2, searchResult.getRoot().getChilds().size());
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {

    }

}
