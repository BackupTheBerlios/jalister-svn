package directorylister.gui.actions.io.plugins;

import directorylister.controllers.JaListerDatabaseController;
import directorylister.gui.filters.JaListerDatabaseFileFilter;
import directorylister.model.JaListerDatabase;
import directorylister.notification.Notification;
import directorylister.notification.ProgressListener;
import directorylister.search.Searcher;
import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Plugin for opening saved databases.
 *
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 13:56:40
 */
public class OpenJaListerDatabasePlugin implements FileOpenSavePlugin {
    private static final Log logger = LogFactory.getLog(OpenJaListerDatabasePlugin.class.getName());

    public void handleFile(File selectedFile, ProgressListener listener) {
        try {
            final InputStream inputStream = new FileInputStream(selectedFile);
            listener.notify(new Notification("Loading database..."));
            final JaListerDatabase listerDatabase = (JaListerDatabase) SerializationUtils.deserialize(inputStream);
            listener.notify(new Notification("Building indexes..."));
            listerDatabase.attachService(new Searcher());
            JaListerDatabaseController.getInstance().setCurrentJaListerDatabase(listerDatabase);
            listener.notify(new Notification("Done."));

        } catch(FileNotFoundException e) {
            logger.error(e.toString());
        }
        catch(SerializationException e) {
            logger.error(e.toString());
        }
    }

    public JFileChooser getFileChooser() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new JaListerDatabaseFileFilter());
        return fileChooser;
    }

    public String getFileExtension() {
        return ".jalister";
    }

    public boolean isOpenDialog() {
        return true;
    }
}
