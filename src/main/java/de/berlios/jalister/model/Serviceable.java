package de.berlios.jalister.model;

/**
 * Interface for objects, that can have services.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.08.2007 2:07:45
 */
public interface Serviceable<T extends Serviceable<? extends T>> {

    /**
     * Attaches service to the object.
     *
     * @param service to attach.
     * @return <code>true</code> if service was attached, <code>false</code> otherwise.
     */
    boolean attachService(final Service<T> service);

    /**
     * Removes service from object.
     *
     * @param service to remove.
     * @return <code>true</code> if service was detached, <code>false</code> otherwise.
     */
    boolean detachService(final Service<T> service);

}
