package directorylister.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Stack;

/**
 * XML Serializer for FileEntry class.
 *
 * @author bg bgdotmail@gmail.com
 * @since 02.08.2007 23:41:01
 */
public class XMLSerializerFileEntryVisitor extends FileEntryVisitorAdapter {
    /**
     * Field stack
     */
    private final Stack<Element> stack;
    /**
     * Field document
     */
    private final Document document;
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(XMLSerializerFileEntryVisitor.class);

    /**
     * Constructor XMLSerializerFileEntryVisitor creates a new XMLSerializerFileEntryVisitor instance.
     *
     * @param document of type Document
     */
    public XMLSerializerFileEntryVisitor(Document document) {
        this.document = document;
        stack = new Stack<Element>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void acceptEntry(FileEntry fileEntry) {
        Element lastEntry = stack.lastElement();
        Element node = document.createElement(fileEntry.isDirectory() ? "directory" : "file");
        lastEntry.appendChild(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void levelStarted(FileEntry newRoot) {
        Element xmlNode =
                document.createElement("fileEntry");
        xmlNode.setAttribute("type", newRoot.isDirectory() ? "directory" : "file");
        xmlNode.setAttribute("shortName", newRoot.getShortName());
        xmlNode.setAttribute("lastModified", String.valueOf(newRoot.getLastModified()));

        for (FileEntryMetaData data : newRoot.getMetadatas()) {
            xmlNode.setAttribute(String.valueOf(data.getKey()), String.valueOf(data.getValue()));
        }
        document.appendChild(xmlNode);
        stack.push(xmlNode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void levelEnded(FileEntry entry) {
        stack.pop();
    }
}
