package directorylister.gui;

import javax.swing.JOptionPane;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 12:37:57
 */
public class NarrowOptionPane extends JOptionPane {
    int maxCharactersPerLineCount;

    public NarrowOptionPane(int maxCharactersPerLineCount) {
        this.maxCharactersPerLineCount = maxCharactersPerLineCount;
    }

    public int getMaxCharactersPerLineCount() {
        return maxCharactersPerLineCount;
    }
}
