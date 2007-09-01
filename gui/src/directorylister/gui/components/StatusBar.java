package directorylister.gui.components;

import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 10:21:59
 */
public class StatusBar extends JPanel {

    public StatusBar() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        final FreeMemoryIndicator indicator = new FreeMemoryIndicator();

        add(indicator);
    }
}
