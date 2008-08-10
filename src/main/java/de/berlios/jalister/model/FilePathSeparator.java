package de.berlios.jalister.model;

/**
 * Enum for operating systems.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.08.2007 0:25:48
 */
public enum FilePathSeparator {
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
    FilePathSeparator(final String separator) {
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
     * Getter for file path separator.
     *
     * @return file path separator.
     */
    public static FilePathSeparator getFilePathSeparator() {
        final String osName = System.getProperty("os.name").toLowerCase().trim();
        if (osName.contains("linux")) {
            return LINUX;
        } else if (osName.contains("windows")) {
            return WINDOWS;
        } else if (osName.contains("mac")) {
            return MACOSX;
        }
        return UNKNOWN;
    }
}
