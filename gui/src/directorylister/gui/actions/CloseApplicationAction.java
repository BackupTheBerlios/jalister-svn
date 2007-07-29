package directorylister.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 15.07.2007
 * Time: 17:02:31
 * To change this template use File | Settings | File Templates.
 */
public class CloseApplicationAction implements ActionListener {

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
