package SpaceShoother;

import javax.swing.*;
import java.awt.*;

public class PowerUp_LeftWeapon {
    private int x;
    private int y;
    private ImageIcon leftWeapon;

    private MainFrame mainFrame;
    private Controller controller;

    public PowerUp_LeftWeapon(MainFrame mainFrame, int x, int y) {
        this.x = x;
        this.y = y;
        this.mainFrame = mainFrame;


        leftWeapon = mainFrame.getBulletImageIcon();
    }

    public void updating() {
        y -= 5;
    }


    public void draw(Graphics graphics) {
        leftWeapon.paintIcon(mainFrame, graphics, x, y);
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
