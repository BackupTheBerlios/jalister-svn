package de.berlios.jalister.utils;

import java.awt.*;

/**
 * Defines interface for processing AWT components.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 29.07.2007 23:58:08
 */
public interface ComponentProcessor {

    /**
     * Processes one component.
     *
     * @param component - component to process.
     */
    void process(final Component component);
}
