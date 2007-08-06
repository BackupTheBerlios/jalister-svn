package directorylister.gui.actions;

import directorylister.gui.components.AboutBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 29.07.2007 23:09:10
 */
public class ShowAboutBoxAction implements ActionListener {
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        new AboutBox().setVisible(true);
    }
}
