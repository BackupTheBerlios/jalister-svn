package de.berlios.jalister.model;

import java.io.Serializable;

/**
 * Interface for services for <code>JaListerDatabase</code>
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.08.2007 2:08:29
 */
public interface Service<T extends Serviceable> extends Serializable, Nameable {

    /**
     * Method notifyAttached ...
     *
     * @param serviceable of type T
     */
    void notifyAttached(T serviceable);
}
