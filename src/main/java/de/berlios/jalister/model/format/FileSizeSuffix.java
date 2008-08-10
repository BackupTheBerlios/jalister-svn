package de.berlios.jalister.model.format;

import de.berlios.jalister.model.Localizable;

/**
 * @author schakal Oleg Atamanenko
 * @since 03.09.2007 14:47:08
 */
enum FileSizeSuffix implements Localizable {
    /**
     * Field BYTES
     */
    BYTES(1, "FileSizeFormatter.bytes"),
    /**
     * Field KB
     */
    KB(2L << 9, "FileSizeFormatter.kb"),
    /**
     * Field MB
     */
    MB(2L << 19, "FileSizeFormatter.mb"),
    /**
     * Field GB
     */
    GB(2L << 29, "FileSizeFormatter.gb"),
    /**
     * Field TB
     */
    TB(2L << 39, "FileSizeFormatter.tb");

    /**
     * Field resolution
     */
    private final long resolution;
    /**
     * Field localizationKey
     */
    private final String localizationKey;

    /**
     * Method getSuffix ...
     *
     * @param value of type double
     * @return FileSizeSuffix
     */
    public static FileSizeSuffix getSuffix(final double value) {
        FileSizeSuffix result = BYTES;
        for (final FileSizeSuffix suffix : values()) {
            if (value > suffix.resolution) {
                result = suffix;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Constructor FileSizeSuffix creates a new FileSizeSuffix instance.
     *
     * @param resolution      of type long
     * @param localizationKey of type String
     */
    FileSizeSuffix(final long resolution, final String localizationKey) {
        this.resolution = resolution;
        this.localizationKey = localizationKey;
    }

    /**
     * Method getSize ...
     *
     * @param size of type long
     * @return double
     */
    public double getSize(final long size) {
        return size / (double) resolution;
    }

    /**
     * @see Localizable#getLocalizationKey()
     */
    public String getLocalizationKey() {
        return localizationKey;
    }
}
