package directorylister.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import directorylister.gui.components.SettingsDialog;

/**
 * Opens settings dialog.
 *
 * @author schakal Oleg Atamanenko
 * @since 27.10.2007 7:12:53
 */
public class ShowSettingsDialog implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        final SettingsDialog dialog = new SettingsDialog();
        dialog.setModal(true);
        dialog.setVisible(true);
    }
}
