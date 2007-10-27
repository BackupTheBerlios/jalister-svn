package directorylister.gui.components;

import java.awt.HeadlessException;

import javax.swing.JDialog;

/**
 * Settings dialog.
 *
 * @author schakal Oleg Atamanenko
 * @since 27.10.2007 7:11:58
 */
public class SettingsDialog extends JDialog {
    public SettingsDialog() throws HeadlessException {
        // TODO: Save in settings and restore.
        setSize(640, 480);
    }
}
