package de.berlios.jalister.resources;

import de.berlios.jalister.utils.SwingUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Performs localization of the GUI.
 *
 * @author schakal Oleg Atamanenko
 * @since 22.07.2007 15:07:49
 */
public final class Localizer {
    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(Localizer.class.getName());
    /**
     * Field processor
     */
    private final ComponentProcessor processor;

    /**
     * Constructs a new Localizer.
     */
    public Localizer() {
        processor = new ComponentProcessor();
    }


    /**
     * Localizes given component and all of its subcomponents.
     *
     * @param component - component to localize.
     */
    public void localize(final Component component) {
        SwingUtils.processWithSubComponents(component, processor);
    }

    /**
     * Class ComponentProcessor ...
     *
     * @author schakal
     *         Created on 05.08.2007
     */
    private static class ComponentProcessor implements de.berlios.jalister.utils.ComponentProcessor {

        /**
         * Method getValueForComponent ...
         *
         * @param componentName of type String
         * @return String
         */
        private static String getValueForComponent(final String componentName) {
            return ResourceHandler.getInstance().getMessage(componentName);
        }

        /**
         * {@inheritDoc}
         *
         * @see de.berlios.jalister.utils.ComponentProcessor#process(Component)
         */
        public void process(final Component component) {
            logger.debug("Processing:" + component.getClass() + " name: " + component.getName());
            final String componentName = component.getName();
            if (componentName == null) {
                return;
            }

            final String value = getValueForComponent(componentName);

            if (component instanceof JLabel) {
                ((JLabel) component).setText(value);
            }
            if (component instanceof Frame) {
                ((Frame) component).setTitle(value);
            }
            if (component instanceof Dialog) {
                ((Dialog) component).setTitle(value);
            }
            if (component instanceof AbstractButton) {
                ((AbstractButton) component).setText(value);
            }
            if (component instanceof JTextComponent) {
                ((JTextComponent) component).setText(value);
            }

            // TODO: Use different keys for tooltips. I.e. add suffix .tooltip.
            if (component instanceof JComponent) {
                ((JComponent) component).setToolTipText(value);
            }

        }
    }
}
