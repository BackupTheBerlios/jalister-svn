package directorylister.model.metadata;

import directorylister.model.Localizable;
import directorylister.model.Nameable;

import java.io.Serializable;

/**
 * Metadata keys should implement this interface.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 1:44:16
 */
public interface MetaDataKey extends Nameable, Serializable, Localizable {

}
