package directorylister.model;

import directorylister.parser.FileSystemParser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:47:12
 */
public class FileSystemParserUnitTest {

    /**
     * Field parser
     */
    private FileSystemParser parser;

    /**
     * Method test ...
     *
     * @throws IOException when
     */
    @Test()
    public void test() throws IOException {
        parser = new FileSystemParser(new File("."));

        final FileEntry fileEntry = parser.parse();

        System.out.println("fileEntry: " + fileEntry);
    }
}
