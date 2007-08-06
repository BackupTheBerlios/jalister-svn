package directorylister.gui.components;

import directorylister.gui.MainWindow;
import directorylister.gui.actions.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: bg
 * Date: 15.07.2007
 * Time: 16:54:23
 */
public class MainMenu extends JMenuBar {


    /**
     * Constructor MainMenu creates a new MainMenu instance.
     *
     * @param mainWindow of type MainWindow
     */
    public MainMenu(MainWindow mainWindow) {
        setName("MainMenu");

        JMenu menuFile = new JMenu();
        menuFile.setName("MainMenu.File");
        add(menuFile);

        JMenuItem fileChooseDirectory = new JMenuItem();
        fileChooseDirectory.setName("MainMenu.ChooseDirectory");
        fileChooseDirectory.addActionListener(new ChooseDirectoryAction());

        JMenuItem fileOpenTree = new JMenuItem();
        fileOpenTree.setName("MainMenu.OpenSavedTree");
        fileOpenTree.addActionListener(new OpenSavedTreeAction(mainWindow));

        JMenuItem fileSave = new JMenuItem();
        fileSave.setName("MainMenu.SaveTree");
        fileSave.addActionListener(new FileSaveTreeAction(mainWindow));

        JMenuItem saveXML = new JMenuItem();
        saveXML.setName("MainMenu.SaveXML");
        saveXML.addActionListener(new FileSaveXMLAction(mainWindow));

        JMenuItem fileCloseApp = new JMenuItem();
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

        JMenu menuSettings = new JMenu();
        menuSettings.setName("MainMenu.Settings");
        add(menuSettings);

        JMenu settingsLanguage = new JMenu();
        settingsLanguage.setName("MainMenu.Settings.Language");
        menuSettings.add(settingsLanguage);

        JMenuItem languageEnglish = new JMenuItem();
        languageEnglish.setName("MainMenu.Settings.Language.English");
        settingsLanguage.add(languageEnglish);
        languageEnglish.addActionListener(new SettingsSetLanguageAction(mainWindow, Locale.ENGLISH));

        JMenuItem languageRussian = new JMenuItem();
        languageRussian.setName("MainMenu.Settings.Language.Russian");
        languageRussian.addActionListener(new SettingsSetLanguageAction(mainWindow, new Locale("RU", "ru")));
        settingsLanguage.add(languageRussian);

        JMenu helpMenu = new JMenu();
        helpMenu.setName("MainMenu.Help");

        JMenuItem aboutBox = new JMenuItem();
        aboutBox.setName("MainMenu.Help.About");
        aboutBox.addActionListener(new ShowAboutBoxAction());

        helpMenu.add(aboutBox);
        add(helpMenu);
    }

}
