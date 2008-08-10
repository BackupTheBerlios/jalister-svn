package de.berlios.jalister.gui.filters;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * File filter for JaLister database.
 *
 * @author schakal Oleg Atamanenko
 * @since 18.08.2007 13:32:42
 */
public class JaListerDatabaseFileFilter extends FileFilter {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accept(final File f) {
        if (f.isDirectory()) {
            return true;
        }

        return f.getAbsolutePath().endsWith(".jalister");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "JaLister Database (.jalister)";
    }
}
