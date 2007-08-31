package directorylister.gui.components;

import directorylister.gui.components.selectedfilespanel.SelectedFilesPanel;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Class WorkspacePanel ...
 *
 * @author schakal
 *         Created on 05.08.2007
 */
public class WorkspacePanel extends JPanel {
    private static final long serialVersionUID = 3230458465057238286L;

    /**
     * Constructs a new WorkspacePanel.
     */
    public WorkspacePanel() {
        setName("WorkspacePanel");

        setEnabled(false);

        setLayout(new BorderLayout());
        final MetaDataPanel panel = new MetaDataPanel();
        //TODO:
        panel.setPreferredSize(new Dimension(0, 300));
        final JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        
        splitPane.setTopComponent(panel);

        final SelectedFilesPanel bottomPanel = new SelectedFilesPanel();
        splitPane.setBottomComponent(bottomPanel);

        add(splitPane, BorderLayout.CENTER);
    }
}
