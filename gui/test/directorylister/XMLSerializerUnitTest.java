package directorylister;

import directorylister.model.FileEntry;
import directorylister.model.FileType;
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
        FileEntry entry = new FileEntry();

        entry.setFileType(FileType.DIRECTORY);
        entry.setFileName("fileName");
        entry.setLastModified(System.currentTimeMillis() / 1000);


        serializer.serialize(outputStream, entry);

        String result = new String(outputStream.toByteArray());

        String expected = "<?xml version=\"1.0\"?>";
        Assert.assertEquals(expected, result);
    }

}
