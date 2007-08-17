package directorylister.search;

import directorylister.model.FileEntry;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 18.08.2007 1:17:04
 */
public class SearchResult {
    /**
     * Field root
     */
    private FileEntry root;

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
}
