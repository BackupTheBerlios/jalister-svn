package directorylister.gui.components.fileentrytree;

import directorylister.model.FileEntry;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 19.08.2007 3:24:31
 */
public class TooltipTree extends JTree {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -7242725135841964962L;

    @Override
    public String getToolTipText(final MouseEvent event) {
        final Point point = event.getPoint();
        final TreePath path = getPathForLocation((int) point.getX(), (int) point.getY());
        if (null != path) {
            final Object pathComponent = path.getLastPathComponent();
            if (null != pathComponent && pathComponent instanceof DefaultMutableTreeNode) {
                final Object userObject = ((DefaultMutableTreeNode) pathComponent).getUserObject();
                if (userObject instanceof FileEntry) {
                    final FileEntry entry = (FileEntry) userObject;
                    return entry.getFileName();
                }
                return userObject.toString();
            }
        }
        return super.getToolTipText(event);
    }
}
