package de.berlios.jalister.xml;

import de.berlios.jalister.XMLSerializer;
import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.FileType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * User: bg
 * Date: 17.07.2007
 * Time: 23:16:05
 */
public class XMLSerializerUnitTest {

    /**
     * Field outputStream
     */
    private ByteArrayOutputStream outputStream;

    /**
     * Method setUp ...
     */
    @Before()
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
    }

    /**
     * Method testThatSimpleFileEntryCanBeSerialized ...
     */
    @Test()
    public void testThatSimpleFileEntryCanBeSerialized() {
        final FileEntry entry = new FileEntry();

        entry.setFileType(FileType.DIRECTORY);
        entry.setFileName("fileName");
        entry.setShortName("shortName");

        XMLSerializer.serialize(outputStream, entry);

        final String result = new String(outputStream.toByteArray());

        final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                System.getProperty("line.separator") +
                "<fileEntry fileName=\"fileName\" fileType=\"DIRECTORY\" shortName=\"shortName\"></fileEntry>";
        Assert.assertEquals(expected, result);
    }

    /**
     * Test that simple entry with child can be serialized
     */
    @Test
    public void complicatedEntryTest() {
        final FileEntry root = new FileEntry();
        root.setFileType(FileType.DIRECTORY);
        root.setFileName("fileName");
        root.setShortName("shortName");

        FileEntry child = new FileEntry();
        child.setFileType(FileType.FILE);
        child.setFileName("childName");
        child.setShortName("shortChildsName");
        root.addChild(child);

        XMLSerializer.serialize(outputStream, root);

        final String result = new String(outputStream.toByteArray());

        final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                System.getProperty("line.separator") +
                "<fileEntry fileName=\"fileName\" fileType=\"DIRECTORY\" shortName=\"shortName\">" +
                System.getProperty("line.separator") +
                "    <fileEntry fileName=\"childName\" fileType=\"FILE\" shortName=\"shortChildsName\"></fileEntry>" +
                System.getProperty("line.separator") +
                "</fileEntry>";

        Assert.assertEquals(expected, result);

    }

}
