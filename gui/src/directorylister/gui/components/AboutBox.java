package directorylister.gui.components;

import directorylister.resources.Localizer;
import directorylister.resources.ResourceHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 29.07.2007 23:12:18
 */
public final class AboutBox extends JDialog {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(AboutBox.class);

    /**
     * Field label
     */
    private final JEditorPane label;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 5426590417757672166L;

    /**
     * Constructs a new AboutBox.
     */
    public AboutBox() {
        setName("AboutBox");
        setSize(400, 200);
        setLocationRelativeTo(null);


        final GridLayout layout = new GridLayout();
        layout.setColumns(1);
        layout.setRows(1);
        setLayout(layout);

        final Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        label = new JEditorPane();
        label.setBorder(border);
        label.setEditable(false);


        final String message = ResourceHandler.getInstance().getMessage("AboutBox.Label");
        label.setContentType("text/html");
        try {
            label.read(new ByteArrayInputStream(message.getBytes()), null);
        } catch(IOException e) {
            logger.error(e);
        }

        add(new JScrollPane(label));


        addWindowFocusListener(new WindowFocusListener() {

            /**
             * {@inheritDoc}
             */
            public void windowGainedFocus(final WindowEvent e) {
            }

            /**
             * {@inheritDoc}
             */
            public void windowLostFocus(final WindowEvent e) {
                dispose();
            }
        });
        new Localizer().localize(this);
    }
}
