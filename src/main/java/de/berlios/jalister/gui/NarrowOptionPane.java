package de.berlios.jalister.gui;

import javax.swing.*;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.09.2007 12:37:57
 */
public class NarrowOptionPane extends JOptionPane {
    int maxCharactersPerLineCount;
    private static final long serialVersionUID = 7841574024523668326L;

    public NarrowOptionPane(final int maxCharactersPerLineCount) {
        this.maxCharactersPerLineCount = maxCharactersPerLineCount;
    }

    @Override
    public int getMaxCharactersPerLineCount() {
        return maxCharactersPerLineCount;
    }
}
