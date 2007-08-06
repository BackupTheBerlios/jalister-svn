package directorylister.search;

import directorylister.model.FileEntry;
import directorylister.model.FileEntryVisitorAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.search.Hits;

import java.io.File;
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
            } catch(IOException e) {
                logger.error(e);
            }
        }

        stack = new Stack<FileEntry>();
    }

    /**
     * Method addParentDirectories ...
     *
     * @param s of type String
     */
    private void addParentDirectories(final String s) {
        final File file = new File(s);
        String parent = file.getParent();
        while (parent != null) {
            searchResult.add(parent);
            parent = new File(parent).getParent();
        }
    }

    /**
     * {@inheritDoc}
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
     */
    @Override
    public void levelStarted(FileEntry newRoot) {
        if (searchResult.contains(newRoot.getFileName())) {
            if (!newRoot.equals(stack.peek())) {
                final FileEntry root = stack.peek();
                final List<FileEntry> childs = root.getChilds();
                for (FileEntry child : childs) {
                    if (child.equals(newRoot)) {
                        stack.push(child);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void levelEnded(FileEntry entry) {
        if (searchResult.contains(entry.getFileName())) {
            stack.pop();
        }
    }
}
