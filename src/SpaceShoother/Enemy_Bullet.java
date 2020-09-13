package SpaceShoother;

import javax.swing.*;
import java.awt.*;

public class Enemy_Bullet {
    private double x;
    private double y;
    private double speed;

    private ImageIcon eb;

    private MainFrame mainFrame;

    public Enemy_Bullet(MainFrame mainFrame, double x, double y) {
        this.x = x;
        this.y = y;
        this.mainFrame = mainFrame;
        eb = mainFrame.getEnemyBullet();
    }

    public void updating() {
        y += 1.6;
    }


    public void draw(Graphics graphics) {
        eb.paintIcon(mainFrame, graphics, (int) x, (int) y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}


