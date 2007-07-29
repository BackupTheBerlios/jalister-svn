package directorylister.gui.components.fileentrytree;

import directorylister.controllers.FileEntryListenerAdapter;
import directorylister.model.FileEntry;
import directorylister.model.transformers.SortedFileEntryListTransformer;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;

/**
 * File Entry Listener. Rebuilds tree in case opening new saved tree.
 *
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:17:20
 */
class FileEntryListener extends FileEntryListenerAdapter {
    private JTree tree;

    public FileEntryListener(final JTree tree) {
        this.tree = tree;
    }

    /**
     * {@inheritDoc}
     */
    public void notifyCurrentFileEntryChanged(FileEntry currentEntry, FileEntry newEntry) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        root.setUserObject(newEntry);

        buildTree(root, newEntry.getChilds(SortedFileEntryListTransformer.TRANSFORMER));

        final DefaultTreeModel treeModel = new DefaultTreeModel(root);
        tree.setModel(treeModel);
        tree.setEnabled(true);
    }

    private void buildTree(DefaultMutableTreeNode root, List<FileEntry> entries) {

        for (FileEntry entry : entries) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry);

            root.add(node);

            List<FileEntry> childs = entry.getChilds(SortedFileEntryListTransformer.TRANSFORMER);
            if (!childs.isEmpty()) {
                buildTree(node, childs);
            }
        }
    }
}
