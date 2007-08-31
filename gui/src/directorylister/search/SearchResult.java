package directorylister.search;

import directorylister.model.FileEntry;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 18.08.2007 1:17:04
 */
public class SearchResult {
// ------------------------------ FIELDS ------------------------------

    /**
     * Field root
     */
    private FileEntry root;

    private int resultCount;

    private long searchTime;

// --------------------- GETTER / SETTER METHODS ---------------------

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(final int resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * Method getRoot returns the root of this SearchResult object.
     *
     * @return the root (type FileEntry) of this SearchResult object.
     */
    public FileEntry getRoot() {
        return root;
    }

    /**
     * Method setRoot sets the root of this SearchResult object.
     *
     * @param root the root of this SearchResult object.
     */
    public void setRoot(final FileEntry root) {
        this.root = root;
    }

    public long getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(final long searchTime) {
        this.searchTime = searchTime;
    }
}
