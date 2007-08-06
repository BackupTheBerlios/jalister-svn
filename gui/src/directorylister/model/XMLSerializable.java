package directorylister.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Classes, providing serialization to XML should implement this interface.
 * User: bg
 * Date: 18.07.2007
 * Time: 0:02:27
 */
public interface XMLSerializable {
    /**
     * Performs serialization of object to XML.
     *
     * @param document - DOM document for building XML.
     * @return - root element of the document.
     */
    public Element serializeToXML(Document document);
}
