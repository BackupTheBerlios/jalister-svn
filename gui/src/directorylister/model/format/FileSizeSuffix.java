package directorylister.model.format;

import directorylister.model.Localizable;

/**
 * @author schakal Oleg Atamanenko
 * @since 03.09.2007 14:47:08
 */
enum FileSizeSuffix implements Localizable {
    BYTES(1, "FileSizeFormatter.bytes"),
    KB(2L << 9, "FileSizeFormatter.kb"),
    MB(2L << 19, "FileSizeFormatter.mb"),
    GB(2L << 29, "FileSizeFormatter.gb"),
    TB(2L << 39, "FileSizeFormatter.tb");

    private final long resolution;
    private final String localizationKey;

    public static FileSizeSuffix getSuffix(double value) {
        FileSizeSuffix result = BYTES;
        for (FileSizeSuffix suffix : values()) {
            if (value > suffix.resolution) {
                result = suffix;
            }
            else {
                break;
            }
        }
        return result;
    }

    private FileSizeSuffix(final long resolution, final String localizationKey) {
        this.resolution = resolution;
        this.localizationKey = localizationKey;
    }

    public double getSize(final long size) {
        return size / (double) resolution;
    }

    public String getLocalizationKey() {
        return localizationKey;
    }
}
