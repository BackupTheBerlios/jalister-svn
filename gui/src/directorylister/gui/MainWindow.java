/**
 *
 */
package directorylister.gui;

import directorylister.gui.components.MainMenu;
import directorylister.gui.components.WorkspacePanel;
import directorylister.gui.components.fileentrytree.FileEntryTree;
import directorylister.utils.SwingUtils;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;


/**
 * @author bg
 */
public final class MainWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 4603441202609788761L;
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final int DEFAULT_PANE_WIDTH = 350;

    /**
     *
     */
    public MainWindow() {
        super();
        setLayout(new BorderLayout());
        setName("MainWindow");

        final JSplitPane splitPane = new JSplitPane();
        add(splitPane, BorderLayout.CENTER);


        final FileEntryTree fileTree = new FileEntryTree();
        final JScrollPane scrollPane = new JScrollPane(fileTree);
        splitPane.setLeftComponent(scrollPane);
        // TODO: Store in settings.
        scrollPane.setPreferredSize(new Dimension(DEFAULT_PANE_WIDTH, 0));
        splitPane.setRightComponent(new WorkspacePanel());

        final JMenuBar menubar = new MainMenu(this);
        setJMenuBar(menubar);

        final JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.PAGE_START);
        final JMenuItem chooseDirectory = SwingUtils.findComponent("MainMenu.ChooseDirectory", menubar);
        toolBar.add(SwingUtils.createButton(chooseDirectory));

        final JMenuItem openSavedTree = SwingUtils.findComponent("MainMenu.OpenSavedTree", menubar);
        toolBar.add(SwingUtils.createButton(openSavedTree));

        final JMenuItem saveTree = SwingUtils.findComponent("MainMenu.SaveTree", menubar);
        toolBar.add(SwingUtils.createButton(saveTree));

        toolBar.addSeparator();
        final JMenuItem saveXML = SwingUtils.findComponent("MainMenu.SaveXML", menubar);
        toolBar.add(SwingUtils.createButton(saveXML));

        toolBar.addSeparator();

        final JMenuItem close = SwingUtils.findComponent("MainMenu.Close", menubar);
        toolBar.add(SwingUtils.createButton(close));

        // TODO: store in settings.
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
