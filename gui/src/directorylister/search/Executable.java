package directorylister.search;


/**
 * Defines interface for executing with some parameter.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 05.08.2007 1:20:49
 */
public interface Executable<T, E extends Exception> {

    /**
     * Method execute ...
     *
     * @param t of type T
     * @throws E when
     */
    void execute(T t) throws E;
}
