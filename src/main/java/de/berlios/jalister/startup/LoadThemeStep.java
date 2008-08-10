package de.berlios.jalister.startup;

import com.jgoodies.looks.Options;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.ResourceHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:44:49
 */
public class LoadThemeStep extends AbstractStartupStep {

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(LoadThemeStep.class.getName());

    public LoadThemeStep(final ProgressNotifier progressNotifier) {
        super(progressNotifier);
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        progressNotifier.notifyListeners(new Notification(ResourceHandler.getInstance().getMessage("Startup.LoadingTheme")));
        UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
        try {
            UIManager.setLookAndFeel(Options.getCrossPlatformLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {
            logger.warn(e.toString());
        }
        catch (InstantiationException e) {
            logger.warn(e.toString());
        }
        catch (IllegalAccessException e) {
            logger.warn(e.toString());
        }
        catch (UnsupportedLookAndFeelException e) {
            logger.warn(e.toString());
        }
    }
}
