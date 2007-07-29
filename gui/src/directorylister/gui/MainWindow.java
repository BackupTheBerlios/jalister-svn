/**
 *
 */
package directorylister.gui;

import directorylister.gui.components.MainMenu;
import directorylister.gui.components.WorkspacePanel;
import directorylister.gui.components.fileentrytree.FileEntryTree;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


/**
 * @author bg
 */
public class MainWindow extends JFrame {

    private JMenuBar menubar;
    private FileEntryTree fileTree;
    /**
     *
     */
    private static final long serialVersionUID = 4603441202609788761L;

    /**
     *
     */
    public MainWindow() {
        super();
        setLayout(new BorderLayout());
        setName("MainWindow");

        final JSplitPane splitPane = new JSplitPane();
        add(splitPane);


        fileTree = new FileEntryTree();
        JScrollPane scrollPane = new JScrollPane(fileTree);
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(new WorkspacePanel());

        menubar = new MainMenu(this);
        setJMenuBar(menubar);

        setSize(800, 600);

        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        addComponentListener(new ComponentAdapter() {
            /**
             * {@inheritDoc}
             */
            public void componentShown(ComponentEvent componentEvent) {
                splitPane.setDividerLocation(0.4);
            }
        });
    }
}
