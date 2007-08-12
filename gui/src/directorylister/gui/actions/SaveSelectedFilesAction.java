package directorylister.gui.actions;

import directorylister.model.FileEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Saves selected files in the file.
 *
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 15:56:29
 */
public class SaveSelectedFilesAction implements ActionListener {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(SaveSelectedFilesAction.class.getName());

    /**
     * Field list
     */
    private final JList list;

    /**
     * Constructor SaveSelectedFilesAction creates a new SaveSelectedFilesAction instance.
     *
     * @param model of type JList
     */
    public SaveSelectedFilesAction(final JList model) {
        this.list = model;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent e) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);

        final File selectedFile = fileChooser.getSelectedFile();
        if (null == selectedFile) {
            return;
        }

        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter(new BufferedWriter(new FileWriter(selectedFile)));
            final DefaultListModel listModel = (DefaultListModel) list.getModel();
            @SuppressWarnings({"unchecked"})
            final Enumeration<FileEntry> enumeration = (Enumeration<FileEntry>) listModel.elements();

            while (enumeration.hasMoreElements()) {
                outputStream.println(enumeration.nextElement().getFileName());
            }
        } catch (FileNotFoundException e0) {
            logger.error(e0.toString());
        }
        catch (IOException e1) {
            logger.error(e1.toString());

        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
