package de.berlios.jalister.model.metadata.value;

import de.berlios.jalister.model.format.FileSizeFormatter;
import de.berlios.jalister.model.format.Formatter;

/**
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 20:56:10
 */
public class FileSizeMetaDataValue extends DefaultMetaDataValue<Long> {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 7980462317011099619L;

    private static final Formatter<Long, String> formatter = new FileSizeFormatter();

    public FileSizeMetaDataValue(final long fileSize) {
        super(fileSize);
    }

    @Override
    public String getFormattedValue() {
        return formatter.format(getValue());
    }


}
