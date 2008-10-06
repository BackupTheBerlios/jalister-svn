package de.berlios.jalister.parser;

import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.FileType;
import de.berlios.jalister.model.JaListerDatabase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

/**
 * User: bg
 * Date: 12.08.2008
 * Time: 1:41:22
 */
public class XMLParserUnitTest {
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testThatSimpleFileEntryCanBeDeserialized() throws Exception {
        final FileEntry entry = new FileEntry();
        entry.setFileType(FileType.DIRECTORY);
        entry.setFileName("fileName");
        entry.setShortName("shortName");
        final String input = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                System.getProperty("line.separator") +
                "<fileEntry fileName=\"fileName\" fileType=\"DIRECTORY\" shortName=\"shortName\"></fileEntry>";


        File file = File.createTempFile("test", null);
        file.setWritable(true);
        FileWriter writer = new FileWriter(file);
        writer.write(input);
        writer.close();
        XMLParser parser = new XMLParser(file);
        final JaListerDatabase result;

        result = parser.parse();

        Assert.assertEquals(entry, result.getRootEntry());
    }
}
