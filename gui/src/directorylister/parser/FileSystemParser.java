package directorylister.parser;

import directorylister.io.FileUtils;
import directorylister.model.FileEntry;
import directorylister.model.FileEntryBuilder;
import directorylister.model.JaListerDatabase;
import directorylister.notification.ProgressNotifier;
import directorylister.search.Searcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:39:55
 */
public class FileSystemParser extends ProgressNotifier {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(FileSystemParser.class);
    /**
     * Field startFile
     */
    private final File startFile;
    /**
     * Field fileEntryBuilder
     */
    private FileEntryBuilder fileEntryBuilder;

    // TODO: Filter should take it parameters from settings manager.
    /**
     * Field FILTER
     */
    private static final FileFilter FILTER = new FileFilter() {
        /**
         * {@inheritDoc}
         */
        public boolean accept(final File pathname) {
            if (pathname.getName().equals(".svn")) {
                return false;
            }
            // TODO. Need someway to show in gui, that given entry is link.
            // Probably, we need to create enumeration of filetypes.
            return !FileUtils.isLink(pathname);
        }
    };

    /**
     * Constructor FileSystemParser creates a new FileSystemParser instance.
     *
     * @param startFile of type File
     */
    public FileSystemParser(final File startFile) {
        assert startFile.exists();
        this.startFile = startFile;
        fileEntryBuilder = new FileEntryBuilder();
    }

    /**
     * Method parse ...
     *
     * @return FileEntry
     * @throws IOException when
     */
    public JaListerDatabase parse() throws IOException {
        
        notifyListeners("Parser.ParsingFile", startFile);
        final FileEntry rootEntry = fileEntryBuilder.buildFrom(startFile);
        parse(startFile, rootEntry);

        final JaListerDatabase jaListerDatabase = new JaListerDatabase();
        jaListerDatabase.setRootEntry(rootEntry);
        notifyListeners("Parser.BuildingIndices");
        jaListerDatabase.attachService(new Searcher());
        notifyListeners("Parser.Done");
        return jaListerDatabase;
    }

    /**
     * Method parse ...
     *
     * @param startFile of type File
     * @param result    of type FileEntry
     * @return FileEntry
     * @throws IOException when
     */
    private FileEntry parse(final File startFile, final FileEntry result) throws IOException {
        final File[] files = startFile.listFiles(FILTER);
        if (null != files) {
            for (final File file : files) {
                logger.debug("Parsing: " + file.getAbsolutePath());
                final FileEntry fileEntry = fileEntryBuilder.buildFrom(file);
                result.addChild(fileEntry);
                notifyListeners("Parser.ParsingEntry", file);
                if (file.isDirectory() && file.exists()) {
                    parse(file, fileEntry);
                }
            }
        }

        return result;
    }
}
