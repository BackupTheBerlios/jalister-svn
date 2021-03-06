package de.berlios.jalister.gui.components;

import de.berlios.jalister.gui.components.selectedfilespanel.SelectedFilesPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Class WorkspacePanel ...
 *
 * @author schakal
 *         Created on 05.08.2007
 */
public class WorkspacePanel extends JPanel {
    /**
     * Field serialVersionUID
     */
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
