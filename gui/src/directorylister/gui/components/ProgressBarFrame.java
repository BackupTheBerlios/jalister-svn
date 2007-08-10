package directorylister.gui.components;

import directorylister.notification.Notification;
import directorylister.notification.ProgressListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import java.awt.GridLayout;
import java.awt.HeadlessException;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:52:38
 */
public class ProgressBarFrame extends JFrame implements ProgressListener {
    /**
     * Field progressBar
     */
    protected final JProgressBar progressBar;
    /**
     * Field label
     */
    protected final JLabel label;

    /**
     * Constructs a new ProgressBarFrame.
     *
     * @throws java.awt.HeadlessException in case of error.
     */
    public ProgressBarFrame() throws HeadlessException {
        setSize(600, 80);

        centerWindow();

        GridLayout layout = new GridLayout();
        layout.setColumns(1);
        layout.setRows(2);
        setLayout(layout);

        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        label = new JLabel();
        label.setName("messageLabel");
        label.setBorder(border);

        add(label);

        progressBar = new JProgressBar();
        progressBar.setName("progressBar");
        progressBar.setIndeterminate(true);
        progressBar.setBorder(border);

        add(progressBar);

        setAlwaysOnTop(true);
        setResizable(false);
        setUndecorated(true);
    }

    /**
     * Method centerWindow ...
     */
    protected void centerWindow() {
        setLocationRelativeTo(null);
    }

    /**
     * {@inheritDoc}
     *
     * @see directorylister.notification.ProgressListener#notify(Notification)
     */
    public void notify(final Notification notification) {
        label.setText(notification.getMessage());
    }
}
