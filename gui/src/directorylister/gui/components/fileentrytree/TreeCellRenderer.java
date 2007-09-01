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
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -7238073555022268308L;


    /**
     * {@inheritDoc}
     */
    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded,
                                                  final boolean leaf, final int row, final boolean hasFocus) {
        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        final Object userObject = node.getUserObject();

        final JLabel cellRendererComponent = (JLabel)
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (userObject instanceof FileEntry) {
            final FileEntry entry = (FileEntry) userObject;
            cellRendererComponent.setText(entry.getShortName());
        }
        else {
            cellRendererComponent.setText(userObject.toString());
        }
        return cellRendererComponent;
    }
}
