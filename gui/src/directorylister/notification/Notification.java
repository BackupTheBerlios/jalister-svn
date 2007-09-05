package directorylister.notification;

import java.io.Serializable;

/**
 * Represents notification.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 11.08.2007 4:09:37
 */
public class Notification implements Serializable {
// ------------------------------ FIELDS ------------------------------

    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 395646670362752402L;

    /**
     * Field message
     */
    private String message;

    /**
     * Field minValue
     */
    private int minValue;
    /**
     * Field maxValue
     */
    private int maxValue;

    /**
     * Field currentValue
     */
    private int currentValue;

// --------------------------- CONSTRUCTORS ---------------------------

    /**
     * Constructs a new Notification.
     */
    public Notification() {
    }

    /**
     * Constructor Notification creates a new Notification instance.
     *
     * @param message of type String
     */
    public Notification(final String message) {
        this.message = message;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    /**
     * Getter for property 'currentValue'.
     *
     * @return Value for property 'currentValue'.
     */
    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * Setter for property 'currentValue'.
     *
     * @param currentValue Value to set for property 'currentValue'.
     */
    public void setCurrentValue(final int currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Getter for property 'maxValue'.
     *
     * @return Value for property 'maxValue'.
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Setter for property 'maxValue'.
     *
     * @param maxValue Value to set for property 'maxValue'.
     */
    public void setMaxValue(final int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Getter for property 'message'.
     *
     * @return Value for property 'message'.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for property 'message'.
     *
     * @param message Value to set for property 'message'.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Getter for property 'minValue'.
     *
     * @return Value for property 'minValue'.
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * Setter for property 'minValue'.
     *
     * @param minValue Value to set for property 'minValue'.
     */
    public void setMinValue(final int minValue) {
        this.minValue = minValue;
    }
}
