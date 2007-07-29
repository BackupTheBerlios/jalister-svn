package directorylister.gui.components;

import directorylister.controllers.AbstractFileEntryListener;
import directorylister.controllers.FileEntryController;
import directorylister.model.FileEntry;
import directorylister.model.transformers.SortedFileEntryListTransformer;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;

/**
 *
 */
public class FileEntryTree extends JTree {

    private FileEntryListener fileEntryListener;

    /**
     * Constructs a new FileEntryTree.
     */
    public FileEntryTree() {
        setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
        fileEntryListener = new FileEntryListener();
        FileEntryController.getInstance().addListener(fileEntryListener);
        setName("FileTree");
        setEnabled(false);
    }


    private class FileEntryListener extends AbstractFileEntryListener {

        /**
         * {@inheritDoc}
         */
        public void notifyCurrentFileEntryChanged(FileEntry currentEntry, FileEntry newEntry) {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode();
            root.setUserObject(newEntry.getFileName());

            buildTree(root, newEntry.getChilds(SortedFileEntryListTransformer.TRANSFORMER));

            final DefaultTreeModel treeModel = new DefaultTreeModel(root);
            setModel(treeModel);
            setEnabled(true);
        }

        private void buildTree(DefaultMutableTreeNode root, List<FileEntry> entries) {

            for (FileEntry entry : entries) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry.getShortName());

                root.add(node);

                List<FileEntry> childs = entry.getChilds(SortedFileEntryListTransformer.TRANSFORMER);
                if (childs.size() > 0) {
                    buildTree(node, childs);
                }
            }
        }
    }

}
