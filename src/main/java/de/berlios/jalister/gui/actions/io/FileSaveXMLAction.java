package de.berlios.jalister.gui.actions.io;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.gui.MainWindow;
import de.berlios.jalister.model.JaListerDatabase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
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
     * Constructor FileSaveXMLAction creates a new FileSaveXMLAction instance.
     *
     * @param mainWindow of type MainWindow
     */
    public FileSaveXMLAction(final MainWindow mainWindow) {

        final MainWindow mainWindow1 = mainWindow;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent actionEvent) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);

        final File selectedFile = fileChooser.getSelectedFile();
        if (null == selectedFile) {
            return;
        }

        final JaListerDatabase listerDatabase = JaListerDatabaseController.getInstance().getCurrentDatabase();

        if (null != listerDatabase) {
            //OutputStream outputStream = null;
            //outputStream = new FileOutputStream(selectedFile);
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = factory.newDocumentBuilder();
            }
            catch (ParserConfigurationException pce) {
                logger.error(pce.toString());
            }
            if (documentBuilder != null) {
                final Document document = documentBuilder.newDocument();
                document.setXmlVersion("1.1");

                final Element xmlNode = listerDatabase.serializeToXML(document);
                document.appendChild(xmlNode);
                /*logger.debug(document.getElementsByTagName("directory").item(0));*/
                try {
                    final FileWriter fWriter = new FileWriter(selectedFile.getAbsolutePath(),
                            false);
                    final XMLSerializer xmlSer = new XMLSerializer(fWriter, null);
                    xmlSer.serialize(document);
                }
                catch (IOException ioe) {
                    logger.error(ioe.toString());
                }
            }
            //  SerializationUtils.serialize(listerDatabase, outputStream);

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