package directorylister.search;

import directorylister.controllers.FileEntryController;
import directorylister.model.FileEntry;
import directorylister.model.FileType;
import org.apache.log4j.BasicConfigurator;
import org.junit.*;

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
     * Constructs a new SearcherUnitTest.
     */
    public SearcherUnitTest() {
    }

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
        final FileEntry entry = new FileEntry();
        entry.setFileName("root");
        entry.setShortName("root");

        FileEntryController.getInstance().setCurrentFileEntry(entry);

        final FileEntry searchResult = searcher.search(entry, "oo");
        Assert.assertEquals(entry, searchResult);
    }

    /**
     * Method testSearchWithChilds ...
     *
     * @throws Exception when
     */
    @Test()
    public void testSearchWithChilds() throws Exception {
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

        FileEntryController.getInstance().setCurrentFileEntry(entry);

        final FileEntry searchResult = searcher.search(entry, "ba");
        Assert.assertEquals(entry, searchResult);
        Assert.assertEquals(2, searchResult.getChilds().size());
    }

    /**
     * Method tearDown ...
     */
    @After()
    public void tearDown() {

    }

}
