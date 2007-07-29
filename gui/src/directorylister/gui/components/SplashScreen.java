package directorylister.gui.components;

import directorylister.startup.StartupListener;
import directorylister.startup.StartupProgressNotifier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:19:00
 */
public class SplashScreen extends JFrame implements StartupListener {
    private JProgressBar progressBar;
    private JLabel label;

    /**
     * Constructs a new SplashScreen.
     */ // TODO: Implement fancy view.
    public SplashScreen() throws HeadlessException {
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

    private void centerWindow() {
        setLocationRelativeTo(null);
    }

    /**
     * {@inheritDoc}
     */
    public void notify(final String message) {
        label.setText(message);
    }

    public static void main(String[] args) {
        SplashScreen screen = new SplashScreen();
        screen.setVisible(true);
        StartupProgressNotifier notifier = new StartupProgressNotifier();
        notifier.addListener(screen);

        notifier.notifyListeners("Starting up...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        notifier.notifyListeners("Done...");
    }
}
