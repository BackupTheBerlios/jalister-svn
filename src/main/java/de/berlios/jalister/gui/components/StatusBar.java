package de.berlios.jalister.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 10:21:59
 */
public class StatusBar extends JPanel {
    private static final long serialVersionUID = 4729733377173807362L;

    public StatusBar() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        final FreeMemoryIndicator indicator = new FreeMemoryIndicator();

        add(indicator);
    }
}
