package de.berlios.jalister.gui.components.fileentrytree;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.model.FileEntry;

import javax.swing.*;
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
final class TreeMouseListener extends MouseAdapter {
    /**
     * Field tree
     */
    private final JTree tree;

    /**
     * Constructor TreeMouseListener creates a new TreeMouseListener instance.
     *
     * @param tree of type JTree
     */
    public TreeMouseListener(final JTree tree) {
        this.tree = tree;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        final FileEntry fileEntry = getFileEntry(e);
        if (null != fileEntry) {
            if (1 == e.getClickCount() && e.getButton() == MouseEvent.BUTTON3) {
                JaListerDatabaseController.getInstance().fileEntryRightClicked(fileEntry);
            } else if (1 == e.getClickCount() && e.getButton() == MouseEvent.BUTTON1) {
                JaListerDatabaseController.getInstance().fileEntrySelected(fileEntry);
            }
        }
    }

    private FileEntry getFileEntry(final MouseEvent e) {
        final TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            final Object userObject = node.getUserObject();
            if (userObject instanceof FileEntry) {
                return (FileEntry) userObject;
            }
        }
        return null;
    }
}
