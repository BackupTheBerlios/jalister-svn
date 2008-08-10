package de.berlios.jalister.startup;

import de.berlios.jalister.gui.MainWindow;
import de.berlios.jalister.notification.Notification;
import de.berlios.jalister.notification.ProgressNotifier;
import de.berlios.jalister.resources.Localizer;
import de.berlios.jalister.resources.ResourceHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:48:37
 */
public class InitializeGUIStep extends AbstractStartupStep {
    private static final int TOOLTIP_INITIAL_DELAY = 400;

    public InitializeGUIStep(final ProgressNotifier progressNotifier) {
        super(progressNotifier);
    }


    /**
     * {@inheritDoc}
     */
    public void execute() {
        final ResourceHandler resourceHandler = ResourceHandler.getInstance();
        progressNotifier.notifyListeners(new Notification(resourceHandler.getMessage("Startup.InitGUI")));
        final MainWindow mainWindow = new MainWindow();

        EventQueue.invokeLater(new Runnable() {
            /**
             * {@inheritDoc}
             */
            public void run() {
                mainWindow.setLocationRelativeTo(null);

                final Localizer localizer = new Localizer();
                localizer.localize(mainWindow);

                mainWindow.setVisible(true);
            }
        });

        final ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
        toolTipManager.setEnabled(true);
        toolTipManager.setInitialDelay(TOOLTIP_INITIAL_DELAY);
        toolTipManager.setLightWeightPopupEnabled(true);

    }
}
