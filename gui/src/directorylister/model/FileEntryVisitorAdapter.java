package directorylister.model;

/**
 * User: solweig
 * Date: 02.08.2007
 * Time: 0:28:34
 */
public class FileEntryVisitorAdapter implements FileEntryVisitor {

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitor#acceptEntry(FileEntry)
     */
    public void acceptEntry(FileEntry fileEntry) {

    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitor#levelStarted(FileEntry)
     */
    public void levelStarted(FileEntry newRoot) {
    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitor#levelEnded(FileEntry)
     */
    public void levelEnded(FileEntry entry) {

    }
}
