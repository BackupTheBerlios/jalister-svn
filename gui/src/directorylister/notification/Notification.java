package directorylister.notification;

import java.io.Serializable;

/**
 * Represents notification.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 11.08.2007 4:09:37
 */
public class Notification implements Serializable {
    /**
     * Field message
     */
    private final String message;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 395646670362752402L;

    /**
     * Constructor Notification creates a new Notification instance.
     *
     * @param message of type String
     */
    public Notification(final String message) {
        this.message = message;
    }

    /**
     * Getter for property 'message'.
     *
     * @return Value for property 'message'.
     */
    public String getMessage() {
        return message;
    }

}
