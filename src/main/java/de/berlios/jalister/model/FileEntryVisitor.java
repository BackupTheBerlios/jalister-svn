package de.berlios.jalister.model;

/**
 * Interface for file entry visitor. if class want to know
 * it should implement this interface.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 31.07.2007 23:55:37
 */
public interface FileEntryVisitor {
    /**
     * Called when next entry is found.
     *
     * @param fileEntry entry.
     */
    void acceptEntry(FileEntry fileEntry);

    /**
     * Called when new level in hierarchy found.
     *
     * @param newRoot new file entry root.
     */
    void levelStarted(FileEntry newRoot);

    /**
     * Called when all subchilds of the entry was detoured.
     *
     * @param entry for which detour was ended.
     */
    void levelEnded(FileEntry entry);
}
