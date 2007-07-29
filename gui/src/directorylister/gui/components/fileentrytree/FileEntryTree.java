package directorylister.gui.components.fileentrytree;

import directorylister.controllers.FileEntryController;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * File Entry tree.
 *
 * @author Oleg Atamanenko.
 */
public class FileEntryTree extends JTree {

    /**
     * Constructs a new FileEntryTree.
     */
    public FileEntryTree() {
        setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
        final FileEntryListener fileEntryListener = new FileEntryListener(this);
        FileEntryController.getInstance().addListener(fileEntryListener);
        setName("FileTree");
        setEnabled(false);

        addMouseListener(new TreeMouseListener(this));
        setCellRenderer(new TreeCellRenderer());
    }

}
