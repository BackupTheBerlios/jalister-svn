package directorylister.gui.actions;

import directorylister.resources.Localizer;
import directorylister.resources.ResourceHandler;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * @author schakal Oleg Atamanenko
 * @since 22.07.2007 21:54:39
 */
public class SettingsSetLanguageAction implements ActionListener {
    private final Component mainWindow;
    private final Locale newLocale;

    public SettingsSetLanguageAction(final Component mainWindow, final Locale newLocale) {
        this.mainWindow = mainWindow;
        this.newLocale = newLocale;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        ResourceHandler.getInstance().setLocale(newLocale);
        Localizer localizer = new Localizer();
        localizer.localize(mainWindow);

        mainWindow.repaint();
    }
}
