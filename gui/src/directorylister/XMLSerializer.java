package directorylister;

import directorylister.model.XMLSerializable;
import org.w3c.dom.Document;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.OutputStream;

/**
 * User: bg
 * Date: 17.07.2007
 * Time: 23:15:50
 */
public class XMLSerializer{
   private static final Log logger = LogFactory.getLog(Main.class);

   public void serialize(OutputStream outputStream, XMLSerializable entry){

      // extract creation of document in the method.
      final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = null;
      try{
         documentBuilder = factory.newDocumentBuilder();
      } catch(ParserConfigurationException e){
         e.printStackTrace();
      }
      if(documentBuilder==null){
         logger.error("documentBuilder is null");
         return;
      }
      Document document = documentBuilder.newDocument();
      document.setXmlVersion("1.0");

      entry.serializeToXML(document);

      // dump document to outputStream.
   }
}
