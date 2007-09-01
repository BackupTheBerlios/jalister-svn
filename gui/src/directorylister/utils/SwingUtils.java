package directorylister.utils;

import directorylister.gui.NarrowOptionPane;
import directorylister.resources.ResourceHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 29.07.2007 23:56:34
 */
public class SwingUtils {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(SwingUtils.class.getName());
    private static final int MAX_CHARACTERS_PER_LINE_COUNT = 96;

    /**
     * Do not instantiate SwingUtils.
     */
    private SwingUtils() {

    }

    /**
     * Method processWithSubComponents ...
     *
     * @param component of type Component
     * @param processor of type ComponentProcessor
     */
    public static void processWithSubComponents(final Component component, final ComponentProcessor processor) {
        processor.process(component);

        if (component instanceof Container) {
            final Component[] components = ((Container) component).getComponents();
            for (final Component subComponent : components) {
                processWithSubComponents(subComponent, processor);
            }
        }
        if (component instanceof JFrame) {
            final JMenuBar menuBar = ((JFrame) component).getJMenuBar();
            if (null != menuBar) {
                final Component[] components = menuBar.getComponents();
                for (final Component subComponent : components) {
                    processWithSubComponents(subComponent, processor);
                }
            }
        }
        if (component instanceof JMenu) {
            final Component[] components = ((JMenu) component).getMenuComponents();
            for (final Component subComponent : components) {
                processWithSubComponents(subComponent, processor);
            }

        }
    }

    /**
     * Method findComponent ...
     *
     * @param componentName of type String
     * @param container     of type Component
     * @return T
     */
    public static <T extends Component> T findComponent(final String componentName, final Component container) {
        final FindComponentProcessor componentProcessor = new FindComponentProcessor<T>(componentName);
        processWithSubComponents(container, componentProcessor);
        return (T) componentProcessor.getFoundComponent();
    }

    /**
     * Method createButton ...
     *
     * @param originalButton of type JMenuItem
     * @return JButton
     */
    public static JButton createButton(final JMenuItem originalButton) {
        // TODO: Looks like hack.
        final JButton result = new JButton();
        try {
            final ActionListener[] listeners = originalButton.getActionListeners();
            for (final ActionListener listener : listeners) {
                result.addActionListener(listener);
            }
            result.setName(originalButton.getName());
        }
        catch(Exception err) {
            logger.error(err);
        }
        return result;
    }

    public static void showError(final String message) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final ResourceHandler resourceHandler = ResourceHandler.getInstance();
                final String localizedMessage = resourceHandler.getFormattedMessage("Application.ErrorOccured", message);
                final String title = resourceHandler.getMessage("Application.Error");

                final JOptionPane optionPane = new NarrowOptionPane(MAX_CHARACTERS_PER_LINE_COUNT);
                optionPane.setMessage(localizedMessage);
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                final JDialog dialog = optionPane.createDialog(null, title);
                dialog.setVisible(true);
            }
        });

    }
}
