package de.berlios.jalister.search;


/**
 * Defines interface for executing with some parameter.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
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
