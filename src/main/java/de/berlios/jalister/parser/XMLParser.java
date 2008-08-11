package de.berlios.jalister.parser;

import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.FileEntryBuilder;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.ResourceHandler;
import de.berlios.jalister.search.Searcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * User: bg
 * Date: 12.08.2008
 * Time: 0:26:03
 */
public class XMLParser extends ProgressNotifier {
    private static final Log logger = LogFactory.getLog(XMLParser.class);
    private Notification notification;
    private File file;
    private FileEntryBuilder fileEntryBuilder;


    /**
     * Constructor
     *
     * @param file XML file
     */
    public XMLParser(File file) {
        assert file.exists();
        this.file = file;
        notification = new Notification();
        fileEntryBuilder = new FileEntryBuilder();
    }

    /**
     * performs XML parsing
     *
     * @return JaListerDatabase
     * @throws IOException  I/O Exception
     * @throws SAXException parse error
     */
    public JaListerDatabase parse() throws IOException, SAXException {
        final ResourceHandler resourceHandler = ResourceHandler.getInstance();
        final String localizedMessage = resourceHandler.getFormattedMessage("Parser.ParsingFile", file);
        notification.setMessage(localizedMessage);
        notifyListeners(notification);
        DocumentBuilder builder;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.fatal(e);
            // TODO: think about localization
            throw new IOException("Could not initialize document builder.");
        }
        Document document = builder.parse(file);
        Element rootNode = (Element) document.getFirstChild();
        final FileEntry rootEntry = fileEntryBuilder.buildFromElement(rootNode);
        parse(rootNode, rootEntry);

        final JaListerDatabase jaListerDatabase = new JaListerDatabase();
        jaListerDatabase.setRootEntry(rootEntry);
        notification.setMessage(resourceHandler.getMessage("Parser.BuildingIndices"));
        notifyListeners(notification);
        jaListerDatabase.attachService(new Searcher());

        notification.setMessage(resourceHandler.getMessage("Parser.Done"));
        notifyListeners(notification);
        return jaListerDatabase;

    }

    private FileEntry parse(Element elem, FileEntry rootEntry) throws IOException, SAXException {
        NodeList nodes = elem.getElementsByTagName("fileEntry");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element child = (Element) nodes.item(i);
            final FileEntry fileEntry = fileEntryBuilder.buildFromElement(child);
            rootEntry.addChild(fileEntry);
            notification.setMessage(
                    ResourceHandler.getInstance().getFormattedMessage("Parser.ParsingEntry", fileEntry.getShortName())
            );
            notifyListeners(notification);
            if (child.hasChildNodes()) {
                parse(child, fileEntry);
            }
        }
        return rootEntry;
    }
}


