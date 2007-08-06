package directorylister.search;

import java.io.IOException;

/**
 * Defines interface for executing with some parameter.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 05.08.2007 1:20:49
 */
public interface Executable<T> {

    /**
     * Method execute ...
     *
     * @param t of type T
     * @throws IOException when
     */
    void execute(T t) throws IOException;
}
