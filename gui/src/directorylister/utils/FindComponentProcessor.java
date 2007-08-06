package directorylister.utils;

import org.apache.commons.lang.StringUtils;

import java.awt.Component;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 30.07.2007 0:03:38
 */
public final class FindComponentProcessor<T extends Component> implements ComponentProcessor {
    /**
     * Field componentName
     */
    private final String componentName;
    /**
     * Field foundComponent
     */
    private T foundComponent;

    /**
     * Constructor FindComponentProcessor creates a new FindComponentProcessor instance.
     *
     * @param componentName of type String
     */
    public FindComponentProcessor(String componentName) {
        this.componentName = componentName;
    }

    /**
     * {@inheritDoc}
     *
     * @see ComponentProcessor#process(Component)
     */
    public void process(Component component) {
        if (StringUtils.isEmpty(componentName)) {
            return;
        }
        if (componentName.equals(component.getName())) {
            this.foundComponent = (T) component;
        }
    }

    /**
     * Getter for property 'foundComponent'.
     *
     * @return Value for property 'foundComponent'.
     */
    public T getFoundComponent() {
        return foundComponent;
    }
}
