package de.berlios.jalister.gui.components.fileentrytree;

import de.berlios.jalister.controllers.JaListerDatabaseController;
import de.berlios.jalister.model.FileEntry;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;

/**
 * File Entry tree.
 *
 * @author Oleg Atamanenko.
 */
public final class FileEntryTree extends JPanel {


    /**
     * Field treeUpdater
     */
    private final TreeUpdater treeUpdater;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -7795558676747892696L;


    /**
     * Constructs a new FileEntryTree.
     */
    public FileEntryTree() {
        final JTree tree = new TooltipTree();
        tree.setName("FileTree");
        tree.setToggleClickCount(1);

        tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
        treeUpdater = new TreeUpdater(tree);
        JaListerDatabaseController.getInstance().addListener(treeUpdater);

        tree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(final TreeSelectionEvent e) {
                final TreePath path = e.getNewLeadSelectionPath();
                if (path != null) {
                    final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    final Object userObject = node.getUserObject();
                    if (userObject instanceof FileEntry) {
                        final FileEntry fileEntry = (FileEntry) userObject;
                        JaListerDatabaseController.getInstance().fileEntrySelected(fileEntry);
                    }
                }
            }
        });

        tree.addMouseListener(new TreeMouseListener(tree));
        tree.setCellRenderer(new TreeCellRenderer());


        setLayout(new BorderLayout());
        add(new JScrollPane(tree), BorderLayout.CENTER);


        final JPanel searchBar = new SearchPanel(treeUpdater);

        add(searchBar, BorderLayout.NORTH);

        tree.setEnabled(false);

    }

}
