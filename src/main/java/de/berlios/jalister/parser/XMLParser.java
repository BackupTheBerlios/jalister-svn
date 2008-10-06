package de.berlios.jalister.parser;

import de.berlios.jalister.model.FileEntry;
import de.berlios.jalister.model.JaListerDatabase;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.ResourceHandler;
import de.berlios.jalister.search.Searcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.load.Persister;
import org.xml.sax.SAXException;

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


    /**
     * Constructor
     *
     * @param file XML file
     */
    public XMLParser(File file) {
        assert file.exists();
        this.file = file;
        notification = new Notification();
    }

    /**
     * performs XML parsing
     *
     * @return JaListerDatabase
     * @throws IOException  I/O Exception
     * @throws SAXException parse error
     */
    public JaListerDatabase parse() throws Exception {
        final ResourceHandler resourceHandler = ResourceHandler.getInstance();
        final String localizedMessage = resourceHandler.getFormattedMessage("Parser.ParsingFile", file);
        notification.setMessage(localizedMessage);
        notifyListeners(notification);
        Serializer serializer = new Persister();
        FileEntry rootEntry = serializer.read(FileEntry.class, file);

        final JaListerDatabase jaListerDatabase = new JaListerDatabase();
        jaListerDatabase.setRootEntry(rootEntry);
        notification.setMessage(resourceHandler.getMessage("Parser.BuildingIndices"));
        notifyListeners(notification);
        jaListerDatabase.attachService(new Searcher());

        notification.setMessage(resourceHandler.getMessage("Parser.Done"));
        notifyListeners(notification);
        return jaListerDatabase;
    }
}


