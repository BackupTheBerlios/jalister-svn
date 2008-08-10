package de.berlios.jalister.model;

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
    public void acceptEntry(final FileEntry fileEntry) {

    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitor#levelStarted(FileEntry)
     */
    public void levelStarted(final FileEntry newRoot) {
    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitor#levelEnded(FileEntry)
     */
    public void levelEnded(final FileEntry entry) {

    }
}
