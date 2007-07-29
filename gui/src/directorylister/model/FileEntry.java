package directorylister.model;


import directorylister.model.transformers.Transformer;
import directorylister.utils.ObjectUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:13:55
 */
public class FileEntry implements Serializable, XMLSerializable {
    private String fileName;
    private String shortName;
    private boolean directory;
    private long lastModified;

    private Set<FileEntryMetaData> metadatas = new HashSet<FileEntryMetaData>();

    /**
     * Setter for property 'fileName'.
     *
     * @param fileName Value to set for property 'fileName'.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Setter for property 'shortName'.
     *
     * @param shortName Value to set for property 'shortName'.
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Setter for property 'directory'.
     *
     * @param directory Value to set for property 'directory'.
     */
    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    /**
     * Setter for property 'lastModified'.
     *
     * @param lastModified Value to set for property 'lastModified'.
     */
    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Setter for property 'md5'.
     *
     * @param md5 Value to set for property 'md5'.
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    private final List<FileEntry> childs = new LinkedList<FileEntry>();
    private String md5;

    /**
     * Getter for property 'shortName'.
     *
     * @return Value for property 'shortName'.
     */
    public String getShortName() {
        return shortName;
    }


    /**
     * Constructs a new FileEntry.
     */
    public FileEntry() {
    }

    public void addMetaData(FileEntryMetaData metaData) {
        metadatas.add(metaData);
    }


    /**
     * Getter for property 'metadatas'.
     *
     * @return Value for property 'metadatas'.
     */
    public Set<FileEntryMetaData> getMetadatas() {
        return metadatas;
    }

    /**
     * Setter for property 'metadatas'.
     *
     * @param metadatas Value to set for property 'metadatas'.
     */
    public void setMetadatas(Set<FileEntryMetaData> metadatas) {
        this.metadatas = metadatas;
    }

    public FileEntry(final String absolutePath, final boolean directory, final long lastModified, final String md5,
                     final String shortName) {
        this.md5 = md5;
        this.lastModified = lastModified;
        this.fileName = absolutePath;
        this.directory = directory;
        this.shortName = shortName;
    }

    public FileEntry(final String fileName, final long lastModified, final String md5, final String shortName) {
        this.md5 = md5;
        this.lastModified = lastModified;
        this.fileName = fileName;
        this.shortName = shortName;
        directory = false;
    }

    /**
     * Getter for property 'fileName'.
     *
     * @return Value for property 'fileName'.
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * Getter for property 'directory'.
     *
     * @return Value for property 'directory'.
     */
    public boolean isDirectory() {
        return directory;
    }


    /**
     * Getter for property 'lastModified'.
     *
     * @return Value for property 'lastModified'.
     */
    public long getLastModified() {
        return lastModified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final FileEntry fileEntry = (FileEntry) o;

        return directory == fileEntry.directory &&
                lastModified == fileEntry.lastModified &&
                fileName.equals(fileEntry.fileName);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result;
        result = fileName.hashCode();
        result = 31 * result + (directory ? 1 : 0);
        result = 31 * result + (int) (lastModified ^ (lastModified >>> 32));
        return result;
    }

    public void addChild(final FileEntry entry) {
        childs.add(entry);
    }

    /**
     * Getter for property 'childs'.
     *
     * @return Value for property 'childs'.
     */
    public List<FileEntry> getChilds() {
        return childs;
    }

    public <T> T getChilds(Transformer<List<FileEntry>, T> transformer) {
        return transformer.transform(childs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ObjectUtils.buildToString(this);
    }


    /**
     * {@inheritDoc}
     */
    public void serializeToXML(Document document) {
        Element rootNode = document.createElement("fileEntry");

        // CR: implement recursive обход FileEntry.
//        if (null == rootNode) {
//            if (directory) {
//                rootNode = document.createElement("directory");
//            } else {
//                //TODO написать обработку хотя сюда мы не должны попасть
//            }
//        }

        Element xmlNode = document.createElement(directory ? "directory" : "file");
        xmlNode.setTextContent(shortName);
        rootNode.appendChild(xmlNode);
        xmlNode.setAttribute("lastModified", "" + lastModified);
        for (FileEntryMetaData data : metadatas) {
            xmlNode.setAttribute(data.key.toString(), data.value.toString());
        }
        if (directory == true) {
            List<FileEntry> childs = getChilds();
            for (FileEntry child : childs) {
                child.serializeToXML(document);
            }
        }
    }
}
