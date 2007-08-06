package directorylister.gui.actions;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import directorylister.controllers.FileEntryController;
import directorylister.gui.MainWindow;
import directorylister.model.FileEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 15.07.2007
 * Time: 18:11:24
 */

public final class FileSaveXMLAction implements ActionListener {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(FileSaveXMLAction.class);

    /**
     * Field mainWindow
     */
    private final MainWindow mainWindow;


    /**
     * Constructor FileSaveXMLAction creates a new FileSaveXMLAction instance.
     *
     * @param mainWindow of type MainWindow
     */
    public FileSaveXMLAction(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);

        File selectedFile = fileChooser.getSelectedFile();
        if (null == selectedFile) {
            return;
        }

        FileEntry fileEntry = FileEntryController.getInstance().getCurrentEntry();

        if (null != fileEntry) {
            //OutputStream outputStream = null;
            //outputStream = new FileOutputStream(selectedFile);
            final DocumentBuilderFactory factory;
            factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = factory.newDocumentBuilder();
            } catch(ParserConfigurationException pce) {
                logger.error(pce.toString());
            }
            Document document;
            if (documentBuilder != null) {
                document = documentBuilder.newDocument();
                document.setXmlVersion("1.1");

                Element xmlNode = fileEntry.serializeToXML(document);
                document.appendChild(xmlNode);
                /*logger.debug(document.getElementsByTagName("directory").item(0));*/
                try {
                    FileWriter fWriter = new FileWriter(selectedFile.getAbsolutePath(),
                            false);
                    XMLSerializer xmlSer = new XMLSerializer(fWriter, null);
                    xmlSer.serialize(document);
                } catch(IOException ioe) {
                    logger.error(ioe.toString());
                }
            }
            //  SerializationUtils.serialize(fileEntry, outputStream);

        }
        /*finally{
           if(outputStream!=null){
              try{
                 outputStream.close();
              } catch(IOException e){
                 logger.error(e.toString());
              }
           }
        }*/
    }
}
