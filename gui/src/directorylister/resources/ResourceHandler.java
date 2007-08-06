package directorylister.resources;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Class ResourceHandler ...
 *
 * @author schakal
 *         Created on 05.08.2007
 */
public class ResourceHandler {

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(ResourceHandler.class);

    /**
     * Field INSTANCE
     */
    private static final ResourceHandler INSTANCE = new ResourceHandler();

    /**
     * Field locale
     */
    private Locale locale;

    /**
     * Field resourceBundle
     */
    private ResourceBundle resourceBundle;

    /**
     * Do not instantiate ResourceHandler.
     */
    private ResourceHandler() {
        // TODO: use locale from settings.
        locale = Locale.ENGLISH;
        reloadResources();
    }

    /**
     * Method reloadResources ...
     */
    private void reloadResources() {
        resourceBundle = ResourceBundle.getBundle("directorylister.resources.messages", locale);
    }

    /**
     * Getter for property 'instance'.
     *
     * @return Value for property 'instance'.
     */
    public static ResourceHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Setter for property 'locale'.
     *
     * @param locale Value to set for property 'locale'.
     */
    public void setLocale(final Locale locale) {
        this.locale = locale;
        reloadResources();

    }

    /**
     * Method getMessage ...
     *
     * @param key of type String
     * @return String
     */
    public String getMessage(final String key) {
        final String value;
        try {
            value = resourceBundle.getString(key);

            if (logger.isDebugEnabled()) {
                logger.debug("Returning value [" + value + "] for key [" + key + "]");
            }

        } catch(MissingResourceException e) {
            logger.error("Cannot find value for key [" + key + "]");
            return key;
        }
        return value;
    }
}
