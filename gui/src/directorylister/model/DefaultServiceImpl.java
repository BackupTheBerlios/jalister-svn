package directorylister.model;

/**
 * Default implementation of the <code>Service</code> interface.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.08.2007 23:11:42
 */
public class DefaultServiceImpl<T extends Serviceable<? super T>> implements Service<T> {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 8403132194803783833L;

    /**
     * {@inheritDoc} ** @see directorylister.model.Nameable#getName()
     */
    public String getName() {
        return null;
    }

    /**
     * Method notifyAttached ...
     *
     * @param serviceable of type T
     */
    public void notifyAttached(final T serviceable) {
    }

}
