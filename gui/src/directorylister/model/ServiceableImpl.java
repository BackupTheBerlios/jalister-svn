package directorylister.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Default implementation of the <code>Serviceable</code> interface.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.08.2007 23:09:11
 */
public class ServiceableImpl implements Serviceable {
    /**
     * Field services
     */
    private final Collection<Service> services = Collections.synchronizedCollection(new LinkedList<Service>());

    /**
     * {@inheritDoc} ** @see directorylister.model.Serviceable#attachService(Service)
     */
    public boolean attachService(final Service service) {
        return !services.contains(service) && services.add(service);
    }

    /**
     * {@inheritDoc} ** @see directorylister.model.Serviceable#detachService(Service)
     */
    public boolean detachService(final Service service) {
        return services.contains(service) && services.remove(service);
    }
}
