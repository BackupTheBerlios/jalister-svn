package de.berlios.jalister.xml;

import de.berlios.jalister.XMLSerializer;
import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.FileType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 17.07.2007
 * Time: 23:16:05
 */
public class XMLSerializerUnitTest {

    /**
     * Field serializer
     */
    private XMLSerializer serializer;

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
        serializer = new XMLSerializer();
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

        serializer.serialize(outputStream, entry);

        final String result = new String(outputStream.toByteArray());

        final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                "<fileEntry fileName=\"fileName\" fileType=\"DIRECTORY\" shortName=\"shortName\"/>";
        Assert.assertEquals(expected, result);
    }

}
