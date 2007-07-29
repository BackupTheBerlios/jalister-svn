package directorylister.model;

import org.w3c.dom.Document;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 18.07.2007
 * Time: 0:02:27
 */
public interface XMLSerializable {
    public void serializeToXML(Document document);
}
