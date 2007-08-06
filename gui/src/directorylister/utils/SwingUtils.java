package directorylister.utils;

import javax.swing.*;
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
            for (Component subComponent : components) {
                processWithSubComponents(subComponent, processor);
            }
        }
        if (component instanceof JFrame) {
            final JMenuBar menuBar = ((JFrame) component).getJMenuBar();
            if (null != menuBar) {
                final Component[] components = menuBar.getComponents();
                for (Component subComponent : components) {
                    processWithSubComponents(subComponent, processor);
                }
            }
        }
        if (component instanceof JMenu) {
            final Component[] components = ((JMenu) component).getMenuComponents();
            for (Component subComponent : components) {
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
        FindComponentProcessor componentProcessor = new FindComponentProcessor<T>(componentName);
        processWithSubComponents(container, componentProcessor);
        return (T) componentProcessor.getFoundComponent();
    }

    /**
     * Method createButton ...
     *
     * @param originalButton of type JMenuItem
     * @return JButton
     */
    public static JButton createButton(JMenuItem originalButton) {
        // TODO: Looks like hack.
        final JButton result = new JButton();
        try {
            ActionListener[] listeners = originalButton.getActionListeners();
            for (ActionListener listener : listeners) {
                result.addActionListener(listener);
            }
            result.setName(originalButton.getName());
        } catch(Exception err) {
            System.err.println("error");
            //TODO implement
        }
        return result;
    }
}
