package de.berlios.jalister.model.metadata.value;

import java.io.Serializable;

/**
 * Metadata values should implement this interface.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.07.2007 1:44:40
 */
public interface MetaDataValue<T> extends Serializable {

    T getValue();
}
