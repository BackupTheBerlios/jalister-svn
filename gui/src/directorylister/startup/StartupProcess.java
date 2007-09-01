package directorylister.startup;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:37:05
 */
public class StartupProcess {

    /**
     * Field steps
     */
    private List<StartupStep> steps = Collections.synchronizedList(new LinkedList<StartupStep>());
    /**
     * Field firstStep
     */
    private StartupStep firstStep = null;
    /**
     * Field lastStep
     */
    private StartupStep lastStep = null;

    public void addStep(final StartupStep startupStep) {
        steps.add(startupStep);
    }

    /**
     * Setter for property 'firstStep'.
     *
     * @param startupStep Value to set for property 'firstStep'.
     */
    public void setFirstStep(final StartupStep startupStep) {
        firstStep = startupStep;
    }

    public void execute() {
        if (null != firstStep) {
            firstStep.execute();
        }

        for (final StartupStep step : steps) {
            step.execute();
        }

        if (null != lastStep) {
            lastStep.execute();
        }
    }

    /**
     * Setter for property 'lastStep'.
     *
     * @param startupStep Value to set for property 'lastStep'.
     */
    public void setLastStep(final StartupStep startupStep) {
        lastStep = startupStep;
    }
}
