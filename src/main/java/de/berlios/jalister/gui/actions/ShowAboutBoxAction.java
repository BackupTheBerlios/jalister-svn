package de.berlios.jalister.gui.actions;

import de.berlios.jalister.gui.components.AboutBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 29.07.2007 23:09:10
 */
public class ShowAboutBoxAction implements ActionListener {
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(final ActionEvent e) {
        new AboutBox().setVisible(true);
    }
}
