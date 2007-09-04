package directorylister.search;

import directorylister.controllers.JaListerDatabaseController;
import directorylister.model.FileEntry;
import directorylister.model.FileEntryVisitorAdapter;
import directorylister.model.JaListerDatabase;
import directorylister.utils.SwingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.search.Hits;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 01.08.2007 0:13:19
 */
public final class IndexSearcherFileEntryVisitor extends FileEntryVisitorAdapter {

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(IndexSearcherFileEntryVisitor.class);

    /**
     * Field searchResult
     */
    private final Set<String> searchResult;

    /**
     * Field stack
     */
    private final Stack<FileEntry> stack;
    /**
     * Field root
     */
    private FileEntry root;

    /**
     * Getter for property 'root'.
     *
     * @return Value for property 'root'.
     */
    public FileEntry getRoot() {
        return root;
    }

    /**
     * Constructor IndexSearcherFileEntryVisitor creates a new IndexSearcherFileEntryVisitor instance.
     *
     * @param hits of type Hits
     */
    public IndexSearcherFileEntryVisitor(final Hits hits) {
        searchResult = new HashSet<String>();
        for (int i = 0; i < hits.length(); i++) {
            try {
                final Document document = hits.doc(i);
                final Field field = document.getField(SearchField.ID.name());
                final String s = field.stringValue();
                addParentDirectories(s);
                searchResult.add(s);
            }
            catch(IOException e) {
                SwingUtils.showError(e.getMessage());
                logger.error(e);
            }
        }

        stack = new Stack<FileEntry>();
    }

    /**
     * Adds all parent directories to search result.
     *
     * @param pathToProcess of type String
     */
    private void addParentDirectories(final String pathToProcess) {
        final String rootFileName = root == null ? "" : root.getFileName();
        final String path = pathToProcess.substring(rootFileName.length());

        final JaListerDatabase jaListerDatabase = JaListerDatabaseController.getInstance().getCurrentDatabase();
        final String fileSeparator = jaListerDatabase.getCreator().getFileSeparator();
        final String[] directories = path.split(fileSeparator);

        final StringBuilder parent = new StringBuilder(rootFileName);
        for (final String directory : directories) {
            if (StringUtils.isNotEmpty(directory)) {
                parent.append(fileSeparator).append(directory);
                searchResult.add(parent.toString());
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitorAdapter#acceptEntry(FileEntry)
     */
    @Override
    public void acceptEntry(final FileEntry fileEntry) {
        final FileEntry copy = fileEntry.cloneFirstLevel();
        if (stack.isEmpty()) {
            root = copy;
            stack.push(root);
            return;
        }
        final FileEntry entry = stack.peek();

        if (searchResult.contains(fileEntry.getFileName())) {
            entry.addChild(copy);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitorAdapter#levelStarted(FileEntry)
     */
    @Override
    public void levelStarted(final FileEntry newRoot) {
        if (searchResult.contains(newRoot.getFileName())) {
            if (!newRoot.equals(stack.peek())) {
                final FileEntry root = stack.peek();
                final List<FileEntry> childs = root.getChilds();
                for (final FileEntry child : childs) {
                    if (child.equals(newRoot)) {
                        stack.push(child);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitorAdapter#levelEnded(FileEntry)
     */
    @Override
    public void levelEnded(final FileEntry entry) {
        if (searchResult.contains(entry.getFileName())) {
            stack.pop();
        }
    }
}
