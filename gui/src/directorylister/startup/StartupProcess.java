package directorylister.startup;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author schakal Oleg Atamanenko
 * @since 27.08.2007 8:37:05
 */
public class StartupProcess {

    private List<StartupStep> steps = Collections.synchronizedList(new LinkedList<StartupStep>());
    private StartupStep firstStep;
    private StartupStep lastStep;

    public void addStep(StartupStep startupStep) {
        steps.add(startupStep);
    }

    /**
     * Setter for property 'firstStep'.
     *
     * @param startupStep Value to set for property 'firstStep'.
     */
    public void setFirstStep(StartupStep startupStep) {
        firstStep = startupStep;
    }

    public void execute() {
        if (null != firstStep) {
            firstStep.execute();
        }

        for (StartupStep step : steps) {
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
    public void setLastStep(StartupStep startupStep) {
        lastStep = startupStep;
    }
}
