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
    public XMLSerializerFileEntryVisitor(final Document document) {
        this.document = document;
        stack = new Stack<Element>();
    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitorAdapter#acceptEntry(FileEntry)
     */
    @Override
    public void acceptEntry(final FileEntry fileEntry) {
        final Element lastEntry = stack.lastElement();
        final Element node = document.createElement(fileEntry.getFileType().name().toLowerCase());
        lastEntry.appendChild(node);
    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitorAdapter#levelStarted(FileEntry)
     */
    @Override
    public void levelStarted(final FileEntry newRoot) {
        final Element xmlNode =
                document.createElement("fileEntry");
        xmlNode.setAttribute("type", newRoot.getFileType().name().toLowerCase());
        xmlNode.setAttribute("shortName", newRoot.getShortName());
        xmlNode.setAttribute("lastModified", String.valueOf(newRoot.getLastModified()));

        for (final FileEntryMetaData data : newRoot.getMetadatas()) {
            xmlNode.setAttribute(String.valueOf(data.getKey()), String.valueOf(data.getValue()));
        }
        document.appendChild(xmlNode);
        stack.push(xmlNode);
    }

    /**
     * {@inheritDoc}
     *
     * @see FileEntryVisitorAdapter#levelEnded(FileEntry)
     */
    @Override
    public void levelEnded(final FileEntry entry) {
        stack.pop();
    }
}
