package de.berlios.jalister.parser;

import de.berlios.jalister.io.FileUtils;
import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.FileEntryBuilder;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.ResourceHandler;
import de.berlios.jalister.search.Searcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;

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
    private Notification notification;
    private static final FileComparator FILE_ENTRY_COMPARATOR = new FileComparator();

    /**
     * Constructor FileSystemParser creates a new FileSystemParser instance.
     *
     * @param startFile of type File
     */
    public FileSystemParser(final File startFile) {
        assert startFile.exists();
        this.startFile = startFile;
        fileEntryBuilder = new FileEntryBuilder();
        notification = new Notification();
    }

    /**
     * Method parse ...
     *
     * @return FileEntry
     * @throws IOException when
     */
    public JaListerDatabase parse() throws IOException {

        final ResourceHandler resourceHandler = ResourceHandler.getInstance();
        final String localizedMessage = resourceHandler.getFormattedMessage("Parser.ParsingFile", startFile);
        notification.setMessage(localizedMessage);
        notifyListeners(notification);
        final FileEntry rootEntry = fileEntryBuilder.buildFrom(startFile);
        parse(startFile, rootEntry);

        final JaListerDatabase jaListerDatabase = new JaListerDatabase();
        jaListerDatabase.setRootEntry(rootEntry);
        notification.setMessage(resourceHandler.getMessage("Parser.BuildingIndices"));
        notifyListeners(notification);
        jaListerDatabase.attachService(new Searcher());

        notification.setMessage(resourceHandler.getMessage("Parser.Done"));
        notifyListeners(notification);
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
        Arrays.sort(files, FILE_ENTRY_COMPARATOR);
        if (null != files) {
            for (final File file : files) {
                logger.debug("Parsing: " + file.getAbsolutePath());
                final FileEntry fileEntry = fileEntryBuilder.buildFrom(file);
                result.addChild(fileEntry);
                notification.setMessage(ResourceHandler.getInstance().getFormattedMessage("Parser.ParsingEntry", file));
                notifyListeners(notification);
                if (file.isDirectory() && file.exists()) {
                    parse(file, fileEntry);
                }
            }
        }

        return result;
    }
}
