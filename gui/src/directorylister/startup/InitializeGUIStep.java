package directorylister.startup;

import directorylister.gui.MainWindow;
import directorylister.notification.ProgressNotifier;
import directorylister.resources.Localizer;

import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

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
        progressNotifier.notifyListeners("Startup.InitGUI");
        final MainWindow mainWindow = new MainWindow();

        SwingUtilities.invokeLater(new Runnable() {
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
