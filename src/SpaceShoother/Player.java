package SpaceShoother;

import javax.swing.*;
import java.awt.*;

public class Player {

    static boolean invincibleTime = false;
    static long timer;
    public long start = System.currentTimeMillis();
    public boolean once = false;
    double x1 = 150, y1 = 800;
    MainFrame mainF;
    Sounds sounds = new Sounds();
    Controller c;
    private ImageIcon player;
    private ImageIcon playerRight;
    private ImageIcon playerLeft;
    private ImageIcon playerDamaged;
    private ImageIcon shield;
    private ImageIcon speedLine;
    private boolean newBullet = false;
    private int x;
    private int y;
    private int lifes;
    private int movement_speedX = 0;
    private int movement_speedY = 0;
    private int prevX;

    public Player(MainFrame mainFrame, Controller c, int x, int y) {
        this.x = x;
        this.y = y;

        player = mainFrame.getPlayerImageIcon();
        playerRight = mainFrame.getPlayerRightImageIcon();
        playerLeft = mainFrame.getPlayerLeft();
        playerDamaged = mainFrame.getPlayerDamaged();
        shield = mainFrame.getShield();
        speedLine = mainFrame.getSpeedLine();

        mainF = mainFrame;
        this.c = c;


        lifes = 4;

    }

    public void updating() {

        if ((invincibleTime) && (System.currentTimeMillis() - timer > 2500))
            invincibleTime = false;

        if ((newBullet) && (once)) {
            y += 1;
            once = false;

        }

        if ((newBullet) && (System.currentTimeMillis() - start > 50)) {
            y -= 10;
            newBullet = false;
        }

        prevX = x;

        x += movement_speedX;
        y += movement_speedY;

        if (x >= 685)
            x = 676;

        else if (x <= 0)
            x = 4;

        else if (y >= 684)
            y = 684;

        else if (y <= 0)
            y = 4;

        enemyHits_theShip();
        enemyBullet_hitsTheShip();

        if (lifes == 0) {
            mainF.newGame();
            MainFrame.state = MainFrame.STATE.MENU;
        }
        // mainF.setRunning(false);


    }

    public void draw(Graphics graphics) {

        if (lifes >= 2) {

            if (x > prevX)
                playerRight.paintIcon(mainF, graphics, x, y);

            else if (x < prevX)
                playerLeft.paintIcon(mainF, graphics, x, y);


            else if (x == prevX)
                player.paintIcon(mainF, graphics, x, y);

        } else if (lifes == 1) {
            playerDamaged.paintIcon(mainF, graphics, x, y);
        }

        graphics.setColor(Color.red);
        graphics.fillRect(x + 10, y + 80, lifes * 20, 8);

        if (invincibleTime)
            shield.paintIcon(mainF, graphics, x - 25, y - 25);


    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void setMovement_speedX(int setMovement_speedX) {
        this.movement_speedX = setMovement_speedX;
    }

    public void setMovement_speedY(int setMovement_speedY) {
        this.movement_speedY = setMovement_speedY;
    }


    public void enemyHits_theShip() {

        String hit_byEnemy = "res\\hitByEnemy.wav";

        for (int j = 0; j < c.enemy.size(); j++) {

            double eX = c.enemy.get(j).getX();
            double eY = c.enemy.get(j).getY();


            if (new Rectangle((int) eX, (int) eY, 95, 45).
                    intersects(new Rectangle(x, y + 35, 95, 35))) {
                sounds.playsound(hit_byEnemy, 0.7);
                Controller.enemies_down++;


                if (!invincibleTime) {
                    lifes--;
                    invincibleTime = true;
                    timer = System.currentTimeMillis();
                    x = 300;
                    y = 600;

                }


                c.enemy.get(j).setHealth(c.enemy.get(j).getHealth() - 1);
                if (c.enemy.get(j).getHealth() == 0) {
                    c.enemy.remove(j);
                    if (c.enemy.size() == 0)
                        c.enemy.add(new Enemy(mainF, (int) (Math.random() * 680), -50));
                }


            }

        }

    }

    public void enemyBullet_hitsTheShip() {

        for (int j = 0; j < c.enemy_bullets.size(); j++) {

            double eX = c.enemy_bullets.get(j).getX();
            double eY = c.enemy_bullets.get(j).getY();

            if (new Rectangle(x, y + 35, 95, 35).
                    intersects(new Rectangle((int) eX, (int) eY, 15, 25))) {
                String hit_byEnemy = "res\\hitByEnemy.wav";
                sounds.playsound(hit_byEnemy, 0.7);


                if (!invincibleTime) {
                    lifes--;
                    invincibleTime = true;
                    timer = System.currentTimeMillis();
                    x = 300;
                    y = 600;
                }


                if (c.enemy_bullets.size() >= 1)
                    c.enemy_bullets.remove(j);
            }


        }

    }

    public boolean isNewBullet() {
        return newBullet;
    }

    public void setNewBullet(boolean newBullet) {
        this.newBullet = newBullet;
    }
}
