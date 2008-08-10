/**
 *
 */
package de.berlios.jalister.gui.components;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Directory chooser.
 *
 * @author bg
 */
public class DirectoryChooser extends JFileChooser {

    /**
     *
     */
    private static final long serialVersionUID = -7627344727710436850L;

    /**
     *
     */
    public DirectoryChooser() {
        super(".");
        init();
    }

    /**
     * @param arg0
     */
    public DirectoryChooser(final String arg0) {
        super(arg0);
        init();
    }

    /**
     * @param arg0
     */
    public DirectoryChooser(final File arg0) {
        super(arg0);
        init();
    }

    /**
     * @param arg0
     */
    public DirectoryChooser(final FileSystemView arg0) {
        super(arg0);
        init();
    }

    /**
     * @param arg0
     * @param arg1
     */
    public DirectoryChooser(final File arg0, final FileSystemView arg1) {
        super(arg0, arg1);
        init();
    }

    /**
     * @param arg0
     * @param arg1
     */
    public DirectoryChooser(final String arg0, final FileSystemView arg1) {
        super(arg0, arg1);
        init();
    }

    /**
     * Method init ...
     */
    private void init() {
        this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }
}
