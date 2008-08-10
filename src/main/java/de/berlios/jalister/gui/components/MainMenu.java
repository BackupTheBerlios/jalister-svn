package de.berlios.jalister.gui.components;

import de.berlios.jalister.gui.MainWindow;
import de.berlios.jalister.gui.actions.CloseApplicationAction;
import de.berlios.jalister.gui.actions.SettingsSetLanguageAction;
import de.berlios.jalister.gui.actions.ShowAboutBoxAction;
import de.berlios.jalister.gui.actions.ShowSettingsDialog;
import de.berlios.jalister.gui.actions.io.ChooseDirectoryAction;
import de.berlios.jalister.gui.actions.io.FileSaveTreeAction;
import de.berlios.jalister.gui.actions.io.FileSaveXMLAction;
import de.berlios.jalister.gui.actions.io.OpenSavedTreeAction;

import javax.swing.*;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 15.07.2007
 * Time: 16:54:23
 */
public class MainMenu extends JMenuBar {
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -2082541485065653892L;


    /**
     * Constructor MainMenu creates a new MainMenu instance.
     *
     * @param mainWindow of type MainWindow
     */
    public MainMenu(final MainWindow mainWindow) {
        setName("MainMenu");

        final JMenu menuFile = new JMenu();
        menuFile.setName("MainMenu.File");
        add(menuFile);

        final JMenuItem fileChooseDirectory = new JMenuItem();
        fileChooseDirectory.setName("MainMenu.ChooseDirectory");
        fileChooseDirectory.addActionListener(new ChooseDirectoryAction(mainWindow));

        final JMenuItem fileOpenTree = new JMenuItem();
        fileOpenTree.setName("MainMenu.OpenSavedTree");
        fileOpenTree.addActionListener(new OpenSavedTreeAction(mainWindow));

        final JMenuItem fileSave = new JMenuItem();
        fileSave.setName("MainMenu.SaveTree");
        fileSave.addActionListener(new FileSaveTreeAction(mainWindow));

        final JMenuItem saveXML = new JMenuItem();
        saveXML.setName("MainMenu.SaveXML");
        saveXML.addActionListener(new FileSaveXMLAction());

        final JMenuItem fileCloseApp = new JMenuItem();
        fileCloseApp.setName("MainMenu.Close");
        fileCloseApp.addActionListener(new CloseApplicationAction());

        menuFile.add(fileChooseDirectory);
        menuFile.addSeparator();
        menuFile.add(fileOpenTree);
        menuFile.add(fileSave);
        menuFile.addSeparator();
        menuFile.add(saveXML);
        menuFile.addSeparator();
        menuFile.add(fileCloseApp);

        final JMenu menuSettings = new JMenu();
        menuSettings.setName("MainMenu.Settings");
        add(menuSettings);

        final JMenu settingsLanguage = new JMenu();
        settingsLanguage.setName("MainMenu.Settings.Language");
        menuSettings.add(settingsLanguage);

        final JMenuItem languageEnglish = new JMenuItem();
        languageEnglish.setName("MainMenu.Settings.Language.English");
        settingsLanguage.add(languageEnglish);
        languageEnglish.addActionListener(new SettingsSetLanguageAction(mainWindow, Locale.ENGLISH));

        final JMenuItem languageRussian = new JMenuItem();
        languageRussian.setName("MainMenu.Settings.Language.Russian");
        languageRussian.addActionListener(new SettingsSetLanguageAction(mainWindow, new Locale("RU", "ru")));
        settingsLanguage.add(languageRussian);


        final JMenuItem menuOptions = new JMenuItem();
        menuOptions.setName("MainMenu.Settings.ConfigureJaLister");
        menuOptions.addActionListener(new ShowSettingsDialog());
        menuSettings.add(menuOptions);

        final JMenu helpMenu = new JMenu();
        helpMenu.setName("MainMenu.Help");

        final JMenuItem aboutBox = new JMenuItem();
        aboutBox.setName("MainMenu.Help.About");
        aboutBox.addActionListener(new ShowAboutBoxAction());

        helpMenu.add(aboutBox);
        add(helpMenu);
    }

}
