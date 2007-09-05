package directorylister.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;

/**
 * Database of directory with files.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.08.2007 0:21:45
 */
public class JaListerDatabase extends ServiceableImpl<JaListerDatabase> implements Serializable, XMLSerializable {

    /**
     * Field creator
     */
    private final OperatingSystem creator;
    /**
     * Field rootEntry
     */
    private FileEntry rootEntry;
    /**
     * Field rootPath
     */
    private String rootPath;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 677004819907933162L;

    /**
     * Constructs a new JaListerDatabase.
     */
    public JaListerDatabase() {
        creator = OperatingSystem.getOperatingSystem();
    }

    /**
     * Getter for property 'creator'.
     *
     * @return Value for property 'creator'.
     */
    public OperatingSystem getCreator() {
        return creator;
    }

    /**
     * Getter for property 'rootPath'.
     *
     * @return Value for property 'rootPath'.
     */
    public String getRootPath() {
        return rootPath;
    }

    /**
     * Setter for property 'rootPath'.
     *
     * @param rootPath Value to set for property 'rootPath'.
     */
    public void setRootPath(final String rootPath) {
        this.rootPath = rootPath;
    }

    /**
     * Getter for property 'rootEntry'.
     *
     * @return Value for property 'rootEntry'.
     */
    public FileEntry getRootEntry() {
        return rootEntry;
    }

    /**
     * Setter for property 'rootEntry'.
     *
     * @param rootEntry Value to set for property 'rootEntry'.
     */
    public void setRootEntry(final FileEntry rootEntry) {
        this.rootEntry = rootEntry;
    }

    /**
     * @see XMLSerializable#serializeToXML(Document)
     */
    public Element serializeToXML(final Document document) {
        // TODO: add properties of the Jalister Database to the document.
        return getRootEntry().serializeToXML(document);
    }

}
