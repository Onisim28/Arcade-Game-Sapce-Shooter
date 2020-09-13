package SpaceShoother;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private MainFrame mainFrame;

    public KeyInput(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void keyPressed(KeyEvent e) {
        mainFrame.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        mainFrame.keyReleased(e);
    }

}
