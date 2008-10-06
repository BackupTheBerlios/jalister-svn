package de.berlios.jalister;

import de.berlios.jalister.model.XMLSerializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.load.Persister;
import org.simpleframework.xml.stream.Format;

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
    private static final String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
    private static final int xmlIdent = 4;

    /**
     * Method serialize ...
     *
     * @param outputStream of type OutputStream
     * @param entry        of type XMLSerializable
     */
    public static void serialize(final OutputStream outputStream, final XMLSerializable entry) {
        Format fmt = new Format(xmlIdent, xmlHeader);
        Serializer serializer = new Persister(fmt);
        try {
            serializer.write(entry, outputStream);
        } catch (Exception e) {
            // TODO: handle  
            logger.error(e);
        }
    }
}
