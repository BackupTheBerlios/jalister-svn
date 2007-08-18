package directorylister.model.metadata;

import java.io.Serializable;

/**
 * Metadata values should implement this interface.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:44:40
 */
public interface MetaDataValue<T> extends Serializable {

    T getValue();
}
