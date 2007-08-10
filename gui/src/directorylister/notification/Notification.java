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
    private final String message;

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
