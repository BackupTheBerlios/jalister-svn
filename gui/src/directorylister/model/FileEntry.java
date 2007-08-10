package directorylister.model;


import directorylister.model.transformers.Transformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(FileEntry.class);
    /**
     * Field fileName
     */
    private String fileName;
    /**
     * Field shortName
     */
    private String shortName;
    /**
     * Field fileType
     */
    private FileType fileType;
    /**
     * Field lastModified
     */
    private long lastModified;
    /**
     * Field parent
     */
    private FileEntry parent;

    /**
     * Field metadatas
     */
    private Set<FileEntryMetaData> metadatas = new HashSet<FileEntryMetaData>();
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -1988882703594070834L;

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
     * Setter for property 'fileType'.
     *
     * @param fileType Value to set for property 'fileType'.
     */
    public void setFileType(FileType fileType) {
        this.fileType = fileType;
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

    /**
     * Field childs
     */
    private final List<FileEntry> childs = new LinkedList<FileEntry>();
    /**
     * Field md5
     */
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

    /**
     * Method addMetaData ...
     *
     * @param metaData of type FileEntryMetaData
     */
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

    /**
     * Constructor FileEntry creates a new FileEntry instance.
     *
     * @param absolutePath of type String
     * @param directory    of type boolean
     * @param lastModified of type long
     * @param md5          of type String
     * @param shortName    of type String
     */
    public FileEntry(final String absolutePath, final FileType directory, final long lastModified, final String md5,
                     final String shortName) {
        this.md5 = md5;
        this.lastModified = lastModified;
        this.fileName = absolutePath;
        this.fileType = directory;
        this.shortName = shortName;
    }

    /**
     * Constructor FileEntry creates a new FileEntry instance.
     *
     * @param fileName     of type String
     * @param lastModified of type long
     * @param md5          of type String
     * @param shortName    of type String
     */
    public FileEntry(final String fileName, final long lastModified, final String md5, final String shortName) {
        this.md5 = md5;
        this.lastModified = lastModified;
        this.fileName = fileName;
        this.shortName = shortName;
        fileType = FileType.FILE;
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
     * Getter for property 'fileType'.
     *
     * @return Value for property 'fileType'.
     */
    public FileType getFileType() {
        return fileType;
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

        return fileType == fileEntry.fileType &&
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
        result = 31 * result + (fileType.ordinal());
        result = 31 * result + (int) (lastModified ^ (lastModified >>> 32));
        return result;
    }

    /**
     * Getter for property 'parent'.
     *
     * @return Value for property 'parent'.
     */
    public FileEntry getParent() {
        return parent;
    }

    /**
     * Setter for property 'parent'.
     *
     * @param parent Value to set for property 'parent'.
     */
    private void setParent(FileEntry parent) {
        this.parent = parent;
    }

    /**
     * Method addChild ...
     *
     * @param entry of type FileEntry
     */
    public void addChild(final FileEntry entry) {
        entry.setParent(this);
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

    /**
     * Method getChilds ...
     *
     * @param transformer of type Transformer<List<FileEntry>, T>
     * @return T
     */
    public <T> T getChilds(Transformer<List<FileEntry>, T> transformer) {
        return transformer.transform(childs);
    }


    /**
     * {@inheritDoc}
     */
    @Override()
    public String toString() {
        return "FileEntry{" +
                "fileName='" + fileName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", fileType=" + fileType +
                ", lastModified=" + lastModified +
                ", metadatas=" + metadatas +
                ", md5='" + md5 + '\'' +
                '}';
    }

    /**
     * {@inheritDoc}
     *
     * @see XMLSerializable#serializeToXML(Document)
     */
    public Element serializeToXML(Document document) {
        //    logger.debug((fileType?"fileType ":"file")+shortName);
        Element xmlNode = document.createElement(fileType.name().toLowerCase());

        xmlNode.setAttribute("Name", shortName);
        xmlNode.setAttribute("lastModified", "" + lastModified);

        for (FileEntryMetaData data : metadatas) {
            xmlNode.setAttribute(data.getKey().toString(), data.getValue().toString());
        }
        //  logger.debug(xmlNode.toString());
        if (fileType.equals(FileType.FILE)) {
            List<FileEntry> childs = getChilds();
            Element el;
            for (FileEntry child : childs) {
                el = child.serializeToXML(document);
                if (null != el) xmlNode.appendChild(el);
            }
        }
        return xmlNode;
    }

    /**
     * Method acceptVisitor ...
     *
     * @param fileEntryVisitor of type FileEntryVisitor
     */
    public void acceptVisitor(FileEntryVisitor fileEntryVisitor) {
        accept(fileEntryVisitor, this);
    }

    /**
     * Method accept ...
     *
     * @param fileEntryVisitor of type FileEntryVisitor
     * @param entry            of type FileEntry
     */
    private void accept(FileEntryVisitor fileEntryVisitor, FileEntry entry) {
        fileEntryVisitor.acceptEntry(entry);
        List<FileEntry> fileEntries = entry.getChilds();

        if (!fileEntries.isEmpty()) {
            fileEntryVisitor.levelStarted(entry);
            for (FileEntry fileEntry : fileEntries) {
                accept(fileEntryVisitor, fileEntry);
            }
            fileEntryVisitor.levelEnded(entry);
        }
    }

    /**
     * Method cloneFirstLevel ...
     *
     * @return FileEntry
     */
    public FileEntry cloneFirstLevel() {
        final FileEntry fileEntry = new FileEntry(fileName, fileType, lastModified, md5, shortName);
        fileEntry.setMetadatas(metadatas);
        return fileEntry;
    }
}
