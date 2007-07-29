package directorylister.gui.actions;

import directorylister.model.FileEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Enumeration;

/**
 * Saves selected files in the file.
 *
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 15:56:29
 */
public class SaveSelectedFilesAction implements ActionListener {
    private static final Log logger = LogFactory.getLog(SaveSelectedFilesAction.class.getName());

    private final JList list;

    public SaveSelectedFilesAction(final JList model) {
        this.list = model;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);

        File selectedFile = fileChooser.getSelectedFile();

        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter(new BufferedWriter(new FileWriter(selectedFile)));
            final DefaultListModel listModel = (DefaultListModel) list.getModel();
            @SuppressWarnings({"unchecked"})
            final Enumeration<FileEntry> enumeration = (Enumeration<FileEntry>) listModel.elements();

            while (enumeration.hasMoreElements()) {
                outputStream.println(enumeration.nextElement().getFileName());
            }
        } catch(FileNotFoundException e0) {
            logger.error(e0.toString());
        }
        catch(IOException e1) {
            logger.error(e1.toString());

        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
