package de.berlios.jalister.gui.components.fileentrytree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.Button;
import org.uispec4j.TextBox;
import org.uispec4j.UISpec4J;
import org.uispec4j.Window;

import javax.swing.*;


/**
 * User: schakal
 * Date: 10.08.2008
 * Time: 20:37:46
 */
public class SearchPanelUnitTest {

    private Button button;
    private TextBox textBox;

    @Before
    public void setUp() {
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(new SearchPanel(new TreeUpdater(new JTree())));
        Window window = new Window(jFrame);

        button = window.getButton("FileEntryTree.SearchButton");
        textBox = window.getTextBox("FileEntryTree.SearchBox");
        UISpec4J.init();
    }

    @After
    public void tearDown() {
        // Add your code here
    }

    @Test
    public void test() {
        // TODO: implement
//        button.getAwtComponent().addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                button.getAwtComponent().setBackground(Color.GREEN);
//            }
//        });
//
//     WindowInterceptor
//      .init(textBox.pressKey(Key.ENTER))
//      .process(new WindowHandler() {
//        public Trigger process(Window window) {
//          return button;
//        }
//      })
//      .run();
//
//        textBox.setText("test string");
//        textBox.pressKey(Key.ENTER);
//
//        Assert.assertTrue(button.foregroundEquals("green").isTrue());
    }
}
