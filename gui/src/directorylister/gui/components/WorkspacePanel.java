package directorylister.gui.components;

import directorylister.gui.components.selectedfilespanel.SelectedFilesPanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * Class WorkspacePanel ...
 *
 * @author schakal
 *         Created on 05.08.2007
 */
public class WorkspacePanel extends JPanel {
    /**
     * Constructs a new WorkspacePanel.
     */
    public WorkspacePanel() {
        setName("WorkspacePanel");

        setEnabled(false);

        setLayout(new BorderLayout());

        final JPanel topPanel = new JPanel();
        final GridLayout layout = new GridLayout();
        layout.setColumns(2);
        layout.setRows(20);
        topPanel.setLayout(layout);

        final JLabel comment = new JLabel("Comment");
        topPanel.add(comment);

        final JTextField textBox = new JTextField();
        topPanel.add(textBox);

        final JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(topPanel);

        final SelectedFilesPanel bottomPanel = new SelectedFilesPanel();
        splitPane.setBottomComponent(bottomPanel);

        add(splitPane, BorderLayout.CENTER);
    }
}
