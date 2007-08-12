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
final class FileEntryListener extends FileEntryListenerAdapter {
    /**
     * Field tree
     */
    private final JTree tree;

    /**
     * Constructor FileEntryListener creates a new FileEntryListener instance.
     *
     * @param tree of type JTree
     */
    public FileEntryListener(final JTree tree) {
        this.tree = tree;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyCurrentFileEntryChanged(final FileEntry currentEntry, final FileEntry newEntry) {
        if (newEntry != null) {
            final DefaultMutableTreeNode root = new DefaultMutableTreeNode();
            root.setUserObject(newEntry);

            buildTree(root, newEntry.getChilds(SortedFileEntryListTransformer.TRANSFORMER));

            final DefaultTreeModel treeModel = new DefaultTreeModel(root);
            tree.setModel(treeModel);
            tree.setEnabled(true);
        }
    }

    /**
     * Method buildTree ...
     *
     * @param root    of type DefaultMutableTreeNode
     * @param entries of type List<FileEntry>
     */
    private void buildTree(final DefaultMutableTreeNode root, final List<FileEntry> entries) {

        for (final FileEntry entry : entries) {
            final DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry);

            root.add(node);

            final List<FileEntry> childs = entry.getChilds(SortedFileEntryListTransformer.TRANSFORMER);
            if (!childs.isEmpty()) {
                buildTree(node, childs);
            }
        }
    }
}
