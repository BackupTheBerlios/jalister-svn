package directorylister.gui.components.selectedfilespanel;

import directorylister.model.FileEntry;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Component;

/**
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:22:41
 */
class ListCellRenderer extends DefaultListCellRenderer {

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
