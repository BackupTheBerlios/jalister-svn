package directorylister.gui.components.fileentrytree;

import directorylister.controllers.JaListerDatabaseListenerAdapter;
import directorylister.model.FileEntry;
import directorylister.model.JaListerDatabase;
import directorylister.model.transformers.SortedFileEntryListTransformer;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Collections;
import java.util.List;

/**
 * File Entry Listener. Rebuilds tree in case opening new saved tree.
 *
 * @author schakal Oleg Atamanenko
 * @since 29.07.2007 16:17:20
 */
final class TreeUpdater extends JaListerDatabaseListenerAdapter {
    /**
     * Field tree
     */
    private final JTree tree;

    /**
     * Constructor JaListerDatabaseListener creates a new JaListerDatabaseListener instance.
     *
     * @param tree of type JTree
     */
    public TreeUpdater(final JTree tree) {
        this.tree = tree;
    }

    /**
     * @see JaListerDatabaseListenerAdapter#notifyJaListerDatabaseChanged(JaListerDatabase,JaListerDatabase)
     */
    @Override
    public void notifyJaListerDatabaseChanged(final JaListerDatabase currentDatabase, final JaListerDatabase newDatabase) {
        if (newDatabase != null) {
            final FileEntry fileEntry = newDatabase.getRootEntry();
            if (null != fileEntry) {
                updateTree(fileEntry);
            }
        }
    }

    /**
     * Method buildTree ...
     *
     * @param root    of type DefaultMutableTreeNode
     * @param entries of type List<FileEntry>
     */
    private static void buildTree(final DefaultMutableTreeNode root, final List<FileEntry> entries) {

        for (final FileEntry entry : entries) {
            final DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry);
            root.add(node);
            final List<FileEntry> childs = entry.getChilds(SortedFileEntryListTransformer.TRANSFORMER);
            if (!childs.isEmpty()) {
                buildTree(node, childs);
            }
        }
    }

    /**
     * Method updateTree ...
     *
     * @param rootEntry of type FileEntry
     */
    public void updateTree(final FileEntry rootEntry) {
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        root.setUserObject(rootEntry);
        buildTree(root, rootEntry == null ? Collections.EMPTY_LIST : rootEntry.getChilds(SortedFileEntryListTransformer.TRANSFORMER));

        final DefaultTreeModel treeModel = new DefaultTreeModel(root);
        tree.setModel(treeModel);
        tree.setEnabled(true);
    }
}
