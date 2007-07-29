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

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        final JLabel cellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        FileEntry entry = (FileEntry) value;
        cellRendererComponent.setText(entry.getFileName());
        return cellRendererComponent;
    }
}
