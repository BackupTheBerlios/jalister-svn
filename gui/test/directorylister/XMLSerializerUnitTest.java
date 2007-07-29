package directorylister;

import directorylister.model.FileEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 17.07.2007
 * Time: 23:16:05
 * To change this template use File | Settings | File Templates.
 */
public class XMLSerializerUnitTest {

    private XMLSerializer serializer;

    private ByteArrayOutputStream outputStream;

    @Before()
    public void setUp() {

        outputStream = new ByteArrayOutputStream();
        serializer = new XMLSerializer();
    }

    @Test()
    @Ignore()
    public void testThatSimpleFileEntryCanBeSerialized() {
        FileEntry entry = new FileEntry();

        entry.setDirectory(true);
        entry.setFileName("fileName");
        entry.setLastModified(System.currentTimeMillis() / 1000);


        serializer.serialize(outputStream, entry);

        String result = new String(outputStream.toByteArray());

        String expected = "<file></file>";
        Assert.assertEquals(expected, result);
    }

}
