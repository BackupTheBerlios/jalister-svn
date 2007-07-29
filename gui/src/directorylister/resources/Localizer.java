package directorylister.resources;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.Component;
import java.awt.Container;

/**
 * Performs localization of the GUI.
 *
 * @author schakal Oleg Atamanenko
 * @since 22.07.2007 15:07:49
 */
public class Localizer {
    private static final Log logger = LogFactory.getLog(Localizer.class.getName());

    /**
     * Constructs a new Localizer.
     */
    public Localizer() {
    }


    /**
     * Localizes given component and all of its subcomponents.
     *
     * @param component - component to localize.
     */
    public void localize(final Component component) {
        processComponent(component);

        if (component instanceof Container) {
            final Component[] components = ((Container) component).getComponents();
            for (Component subComponent : components) {
                processComponent(subComponent);
            }
        }
        if (component instanceof JFrame) {
            final JMenuBar menuBar = ((JFrame) component).getJMenuBar();
            if (null != menuBar) {
                final Component[] components = menuBar.getComponents();
                for (Component subComponent : components) {
                    localize(subComponent);
                }
            }
        }
        if (component instanceof JMenu) {
            final Component[] components = ((JMenu) component).getMenuComponents();
            for (Component subComponent : components) {
                localize(subComponent);
            }

        }
    }

    /**
     * Performs actual localizing.
     *
     * @param component
     */
    private static void processComponent(final Component component) {
        final String componentName = component.getName();
        if (componentName == null) {
            return;
        }

        String value = getValueForComponent(componentName);

        if (component instanceof JLabel) {
            ((JLabel) component).setText(value);
        }
        if (component instanceof JFrame) {
            ((JFrame) component).setTitle(value);
        }
        if (component instanceof JDialog) {
            ((JDialog) component).setTitle(value);
        }
        if (component instanceof AbstractButton) {
            ((AbstractButton) component).setText(value);
        }
    }

    private static String getValueForComponent(final String componentName) {
        return ResourceHandler.getInstance().getMessage(componentName);
    }
}
