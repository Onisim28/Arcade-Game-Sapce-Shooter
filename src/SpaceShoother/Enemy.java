package SpaceShoother;


import javax.swing.*;
import java.awt.*;


public class Enemy {
    long timer = System.currentTimeMillis();
    private double x;
    private double y;
    private double enemySpeed;
    private int health = 1;
    private ImageIcon e;
    private MainFrame mf;

    public Enemy(MainFrame mainFrame, double x, double y) {
        this.x = x;
        this.y = y;

        mf = mainFrame;
        e = mf.getEnemyShip();
        enemySpeed = 0.3 + Math.random();

        if (Controller.level1) {
            health = 1;
        } else if (Controller.level2)
            health = 2;
        else if (Controller.level3)
            health = 2;
        else if (Controller.level4)
            health = 3;
        else if (Controller.level5)
            health = 4;

    }

    public void updating() {

        y += enemySpeed;

    }

    public void draw(Graphics graphics) {

        e.paintIcon(mf, graphics, (int) x, (int) y);

        graphics.setColor(Color.green);

        if (Controller.level1)
            graphics.fillRect((int) x + 10, (int) y - 13, 80, 8);

        else if (Controller.level2)
            graphics.fillRect((int) x + 10, (int) y - 13, health * 40, 8);

        else if (Controller.level3)
            graphics.fillRect((int) x + 10, (int) y - 13, health * 40, 8);

        else if (Controller.level4)
            graphics.fillRect((int) x + 10, (int) y - 13, health * 30 - 10, 8);

        else if (Controller.level5)
            graphics.fillRect((int) x + 10, (int) y - 13, health * 20, 8);


    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}


