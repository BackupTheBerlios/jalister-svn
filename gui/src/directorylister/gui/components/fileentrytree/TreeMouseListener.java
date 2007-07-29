package directorylister.gui.components.fileentrytree;

import directorylister.controllers.FileEntryController;
import directorylister.model.FileEntry;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Tree Mouse Listener. Notifies controller about selection of file entries.
 *
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:18:42
 */
class TreeMouseListener extends MouseAdapter {
    private JTree tree;

    public TreeMouseListener(final JTree tree) {
        this.tree = tree;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (1 == e.getClickCount() && e.getButton() == MouseEvent.BUTTON3) {
            final TreePath path = tree.getPathForLocation(e.getX(), e.getY());
            if (path != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                final Object userObject = node.getUserObject();
                if (userObject instanceof FileEntry) {
                    FileEntryController.getInstance().selectFileEntry((FileEntry) userObject);
                }
            }
        }
    }
}
