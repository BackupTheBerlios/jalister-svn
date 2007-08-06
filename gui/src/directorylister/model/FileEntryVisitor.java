package directorylister.model;

/**
 * Interface for file entry visitor. if class want to know
 * it should implement this interface.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 31.07.2007 23:55:37
 */
public interface FileEntryVisitor {
    /**
     * Called when next entry is found.
     *
     * @param fileEntry entry.
     */
    public void acceptEntry(FileEntry fileEntry);

    /**
     * Called when new level in hierarchy found.
     *
     * @param newRoot new file entry root.
     */
    public void levelStarted(FileEntry newRoot);

    /**
     * Called when all subchilds of the entry was detoured.
     *
     * @param entry for which detour was ended.
     */
    void levelEnded(FileEntry entry);
}
