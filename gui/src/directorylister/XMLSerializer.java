package directorylister;

import directorylister.model.XMLSerializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.OutputStream;

/**
 * User: bg
 * Date: 17.07.2007
 * Time: 23:15:50
 */
public class XMLSerializer {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(Main.class);

    /**
     * Method serialize ...
     *
     * @param outputStream of type OutputStream
     * @param entry        of type XMLSerializable
     */
    public void serialize(final OutputStream outputStream, final XMLSerializable entry) {

        // extract creation of document in the method.
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            e.printStackTrace();
        }
        if (documentBuilder == null) {
            logger.error("documentBuilder is null");
            return;
        }
        final Document document = documentBuilder.newDocument();
        document.setXmlVersion("1.0");

        entry.serializeToXML(document);

        // dump document to outputStream.
    }
}
