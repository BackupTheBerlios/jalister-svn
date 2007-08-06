package directorylister.utils;

import java.awt.Component;

/**
 * Defines interface for processing AWT components.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
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
