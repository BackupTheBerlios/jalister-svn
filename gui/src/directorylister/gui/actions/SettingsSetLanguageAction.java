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
    /**
     * Field mainWindow
     */
    private final Component mainWindow;
    /**
     * Field newLocale
     */
    private final Locale newLocale;

    /**
     * Constructor SettingsSetLanguageAction creates a new SettingsSetLanguageAction instance.
     *
     * @param mainWindow of type Component
     * @param newLocale  of type Locale
     */
    public SettingsSetLanguageAction(final Component mainWindow, final Locale newLocale) {
        this.mainWindow = mainWindow;
        this.newLocale = newLocale;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent e) {
        ResourceHandler.getInstance().setLocale(newLocale);
        final Localizer localizer = new Localizer();
        localizer.localize(mainWindow);

        mainWindow.repaint();
    }
}
