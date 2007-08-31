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
public class ServiceableImpl<T extends Serviceable<? extends T>> implements Serviceable<T> {
    /**
     * Field services
     */
    private final Collection<Service<T>> services = Collections.synchronizedCollection(new LinkedList<Service<T>>());


    /**
     * {@inheritDoc} ** @see directorylister.model.Serviceable#attachService(Service)
     */
    public boolean attachService(final Service<T> service) {
        if (services.contains(service)) {
            return false;
        }
        final boolean added = services.add(service);
        if (added) {
            service.notifyAttached((T) this);
        }
        return added;
    }

    /**
     * {@inheritDoc} ** @see directorylister.model.Serviceable#detachService(Service)
     */
    public boolean detachService(final Service<T> service) {
        return services.contains(service) && services.remove(service);
    }

    /**
     * Method getService ...
     *
     * @param aClass of type Class<Y>
     * @return Y
     */
    public <Y extends Service<? extends T>> Y getService(final Class<Y> aClass) {
        for (final Service<T> service : services) {
            if (service.getClass().equals(aClass)) {
                return (Y) service;
            }
        }
        // TODO: try to instantiate in runtime.
        return null;
    }
}
