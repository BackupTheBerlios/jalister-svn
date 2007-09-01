package directorylister.model;


import directorylister.model.metadata.FileEntryMetaData;
import directorylister.model.transformers.Transformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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
    private String fileName = null;
    /**
     * Field shortName
     */
    private String shortName = null;
    /**
     * Field fileType
     */
    private FileType fileType = null;
    /**
     * Field parent
     */
    private FileEntry parent = null;

    /**
     * Field metadatas
     */
    private Collection<FileEntryMetaData> metadatas = new HashSet<FileEntryMetaData>();
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -1988882703594070834L;

    /**
     * Setter for property 'fileName'.
     *
     * @param fileName Value to set for property 'fileName'.
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Setter for property 'shortName'.
     *
     * @param shortName Value to set for property 'shortName'.
     */
    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    /**
     * Setter for property 'fileType'.
     *
     * @param fileType Value to set for property 'fileType'.
     */
    public void setFileType(final FileType fileType) {
        this.fileType = fileType;
    }

    /**
     * Setter for property 'md5'.
     *
     * @param md5 Value to set for property 'md5'.
     */
    public void setMd5(final String md5) {
        this.md5 = md5;
    }

    /**
     * Field childs
     */
    private final List<FileEntry> childs = new LinkedList<FileEntry>();
    /**
     * Field md5
     */
    private String md5 = null;

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
    public void addMetaData(final FileEntryMetaData metaData) {
        metadatas.add(metaData);
    }


    /**
     * Getter for property 'metadatas'.
     *
     * @return Value for property 'metadatas'.
     */
    public Collection<FileEntryMetaData> getMetadatas() {
        return Collections.unmodifiableCollection(metadatas);
    }

    /**
     * Setter for property 'metadatas'.
     *
     * @param metadatas Value to set for property 'metadatas'.
     */
    public void setMetadatas(final Collection<FileEntryMetaData> metadatas) {
        this.metadatas = metadatas;
    }

    /**
     * Constructor FileEntry creates a new FileEntry instance.
     *
     * @param absolutePath of type String
     * @param directory    of type boolean
     * @param md5          of type String
     * @param shortName    of type String
     */
    public FileEntry(final String absolutePath, final FileType directory, final String md5,
                     final String shortName) {
        this.md5 = md5;
        this.fileName = absolutePath;
        this.fileType = directory;
        this.shortName = shortName;
    }

    /**
     * Constructor FileEntry creates a new FileEntry instance.
     *
     * @param fileName  of type String
     * @param md5       of type String
     * @param shortName of type String
     */
    public FileEntry(final String fileName, final String md5, final String shortName) {
        this.md5 = md5;
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
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final FileEntry fileEntry = (FileEntry) o;

        return fileType == fileEntry.fileType &&
                fileName.equals(fileEntry.fileName);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + fileType.ordinal();
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
    private void setParent(final FileEntry parent) {
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
        return Collections.unmodifiableList(childs);
    }

    /**
     * Method getChilds ...
     *
     * @param transformer of type Transformer<List<FileEntry>, T>
     * @return T
     */
    public <T> T getChilds(final Transformer<List<FileEntry>, T> transformer) {
        return transformer.transform(getChilds());
    }

    public <T> T getMetadatas(final Transformer<Collection<FileEntryMetaData>, T> transformer) {
        return transformer.transform(getMetadatas());
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
                ", metadatas=" + metadatas +
                ", md5='" + md5 + '\'' +
                '}';
    }

    /**
     * {@inheritDoc}
     *
     * @see XMLSerializable#serializeToXML(Document)
     */
    public Element serializeToXML(final Document document) {
        //    logger.debug((fileType?"fileType ":"file")+shortName);
        final Element xmlNode = document.createElement(fileType.name().toLowerCase());

        xmlNode.setAttribute("Name", shortName);

        for (final FileEntryMetaData data : metadatas) {
            xmlNode.setAttribute(data.getKey().toString(), data.getValue().toString());
        }
        //  logger.debug(xmlNode.toString());
        if (fileType.equals(FileType.FILE)) {
            final List<FileEntry> childs = getChilds();
            for (final FileEntry child : childs) {
                Element el = child.serializeToXML(document);
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
    public void acceptVisitor(final FileEntryVisitor fileEntryVisitor) {
        accept(fileEntryVisitor, this);
    }

    /**
     * Method accept ...
     *
     * @param fileEntryVisitor of type FileEntryVisitor
     * @param entry            of type FileEntry
     */
    private static void accept(final FileEntryVisitor fileEntryVisitor, final FileEntry entry) {
        fileEntryVisitor.acceptEntry(entry);
        final List<FileEntry> fileEntries = entry.getChilds();

        if (!fileEntries.isEmpty()) {
            fileEntryVisitor.levelStarted(entry);
            for (final FileEntry fileEntry : fileEntries) {
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
        final FileEntry fileEntry = new FileEntry(fileName, fileType, md5, shortName);
        fileEntry.setMetadatas(metadatas);
        return fileEntry;
    }

    public void addMetaDatas(final Collection<FileEntryMetaData> metaDatas) {
        if (null != metaDatas) {
            for (FileEntryMetaData metaData : metaDatas) {
                addMetaData(metaData);
            }
        }
    }
}
