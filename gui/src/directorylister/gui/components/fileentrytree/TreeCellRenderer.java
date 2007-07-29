package directorylister.gui.components.fileentrytree;

import directorylister.model.FileEntry;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;

/**
 * Renderer for <code>FileEntry</code>.
 *
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:19:45
 */
class TreeCellRenderer extends DefaultTreeCellRenderer {


    /**
     * {@inheritDoc}
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        final Object userObject = node.getUserObject();

        final JLabel cellRendererComponent = (JLabel)
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (userObject instanceof FileEntry) {
            FileEntry entry = (FileEntry) userObject;
            cellRendererComponent.setText(entry.getShortName());
        }
        else {
            cellRendererComponent.setText(userObject.toString());
        }
        return cellRendererComponent;
    }
}
