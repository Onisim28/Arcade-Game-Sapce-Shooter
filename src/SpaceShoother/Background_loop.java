package SpaceShoother;

import javax.swing.*;
import java.awt.*;

public class Background_loop {
    final private int x = 0;
    MainFrame mainFrame;
    private double y;
    private ImageIcon background;

    public Background_loop(MainFrame mainFrame, double y) {

        this.y = y;

        this.mainFrame = mainFrame;
        background = mainFrame.getBackg();
    }

    public void updating() {
        y += 1;

    }

    public void draw(Graphics graphics) {
        background.paintIcon(mainFrame, graphics, x, (int) y);
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
