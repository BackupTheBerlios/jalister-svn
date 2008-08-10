package de.berlios.jalister.startup;

import de.berlios.jalister.notification.ProgressNotifier;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:41:43
 */
public abstract class AbstractStartupStep implements StartupStep {
    /**
     * Field progressNotifier
     */
    protected final ProgressNotifier progressNotifier;

    public AbstractStartupStep(final ProgressNotifier progressNotifier) {
        this.progressNotifier = progressNotifier;
    }

}
