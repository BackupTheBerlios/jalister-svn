package directorylister.parser;

import directorylister.model.FileEntry;
import directorylister.model.FileEntryBuilder;

import java.io.File;
import java.io.IOException;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:39:55
 */
public class FileSystemParser {
    private final File startFile;
    private FileEntryBuilder fileEntryBuilder;

    public FileSystemParser(final File startFile) {
        assert startFile.exists();
        this.startFile = startFile;
        fileEntryBuilder = new FileEntryBuilder();
    }

    public FileEntry parse() throws IOException {
        FileEntry result = fileEntryBuilder.buildFrom(startFile);
        return parse(startFile, result);
    }

    private FileEntry parse(final File startFile, final FileEntry result) throws IOException {
        final File[] files = startFile.listFiles();
        for (File file : files) {
            final FileEntry fileEntry = fileEntryBuilder.buildFrom(file);
            result.addChild(fileEntry);
            if (file.isDirectory()) {
                parse(file, fileEntry);
            }
        }

        return result;
    }
}
