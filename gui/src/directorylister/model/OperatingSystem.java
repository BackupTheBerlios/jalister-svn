package directorylister.model;

/**
 * Enum for operating systems.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.08.2007 0:25:48
 */
public enum OperatingSystem {
    /**
     * Field LINUX
     */LINUX("/"),
    /**
     * Field WINDOWS
     */WINDOWS("\\"),
    /**
     * Field MACOSX
     */MACOSX("/"),
    /**
     * Field UNKNOWN
     */UNKNOWN("/");

    /**
     * Constructor OperatingSystem creates a new OperatingSystem instance.
     *
     * @param separator of type String
     */
    OperatingSystem(final String separator) {
        this.separator = separator;
    }

    /**
     * Field separator
     */
    private final String separator;

    /**
     * Returns file separator for the operating system.
     *
     * @return file separator.
     */
    public String getFileSeparator() {
        return separator;
    }

    /**
     * Getter for property 'operatingSystem'.
     *
     * @return Value for property 'operatingSystem'.
     */
    public static OperatingSystem getOperatingSystem() {
        final String osName = System.getProperty("os.name").toLowerCase().trim();
        if (osName.contains("linux")) {
            return LINUX;
        }
        else if (osName.contains("windows")) {
            return WINDOWS;
        }
        else if (osName.contains("mac")) {
            return MACOSX;
        }
        return UNKNOWN;
    }
}
