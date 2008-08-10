package de.berlios.jalister.gui.components.selectedfilespanel;

import de.berlios.jalister.model.FileEntry;

import javax.swing.*;
import java.awt.*;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:22:41
 */
class ListCellRenderer extends DefaultListCellRenderer {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 7645523489678085320L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        final JLabel cellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        final FileEntry entry = (FileEntry) value;
        cellRendererComponent.setText(entry.getFileName());
        return cellRendererComponent;
    }
}
