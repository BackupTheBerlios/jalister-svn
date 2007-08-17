package directorylister.model;

import java.io.Serializable;

/**
 * Interface for services for <code>JaListerDatabase</code>
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
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
