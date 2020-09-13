package SpaceShoother;

import javax.swing.*;
import java.awt.*;

public class Bullet {

    private int x;
    private int y;
    private ImageIcon bull;

    private MainFrame mainFrame;


    public Bullet(MainFrame mf, int x, int y) {

        this.x = x;
        this.y = y;
        mainFrame = mf;
        bull = mainFrame.getBulletImageIcon();

    }

    public void updating() {
        y -= 5;

    }

    public void draw(Graphics graphics) {

        bull.paintIcon(mainFrame, graphics, x, y);
        //graphics.fillRect((int)x-45, (int)y+65, 10, 30);
        // graphics.setColor(Color.red);

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}
