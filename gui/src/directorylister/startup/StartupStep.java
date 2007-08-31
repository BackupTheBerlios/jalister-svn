package directorylister.startup;

/**
 * Step, executed during startup process.
 *
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:37:35
 */
public interface StartupStep {
    /**
     * Actions, that should be performed during this step.
     */
    public void execute();
}
