package de.berlios.jalister.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Classes, providing serialization to XML should implement this interface.
 *
 * @author bg
 * @since 18.07.2007 0:02:27
 */
public interface XMLSerializable {
    /**
     * Performs serialization of object to XML.
     *
     * @param document - DOM document for building XML.
     * @return - root element of the document.
     */
    Element serializeToXML(final Document document);
}
