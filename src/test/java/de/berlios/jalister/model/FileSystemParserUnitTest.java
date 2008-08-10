package de.berlios.jalister.model;

import de.berlios.jalister.parser.FileSystemParser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:47:12
 */
public class FileSystemParserUnitTest {

    /**
     * Method test ...
     *
     * @throws IOException when
     */
    @Test()
    public void test() throws IOException {
        final FileSystemParser parser = new FileSystemParser(new File("."));

        final JaListerDatabase fileEntry = parser.parse();

        System.out.println("fileEntry: " + fileEntry);
    }
}
