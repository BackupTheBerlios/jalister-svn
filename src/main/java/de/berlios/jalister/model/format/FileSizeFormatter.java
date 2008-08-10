/*
 * FileSizeFormatter.java
 *
 * Created on 3 Сентябрь 2007 г., 20:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.berlios.jalister.model.format;

import de.berlios.jalister.resources.ResourceHandler;

/**
 * @author schakal
 */
public class FileSizeFormatter extends AbstractFormatter<Long, String> {

    /**
     * Creates a new instance of FileSizeFormatter
     */
    public FileSizeFormatter() {
    }

    public String transform(final Long source) {
        final FileSizeSuffix sizeSuffix = FileSizeSuffix.getSuffix(source);
        final double size = sizeSuffix.getSize(source);
        return ResourceHandler.getInstance().getFormattedMessage(sizeSuffix.getLocalizationKey(), size);
    }

}


