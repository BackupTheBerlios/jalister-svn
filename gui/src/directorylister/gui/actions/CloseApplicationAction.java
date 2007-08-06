package directorylister.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: bg
 * Date: 15.07.2007
 * Time: 17:02:31
 */
public class CloseApplicationAction implements ActionListener {

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
