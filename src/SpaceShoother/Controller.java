package SpaceShoother;


import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Controller {

    static int enemies_down;
    static boolean level1 = false;
    static boolean level2 = false;
    static boolean level3 = false;
    static boolean level4 = false;
    static boolean level5 = false;
    public LinkedList<Bullet> bullet = new LinkedList<>();
    public LinkedList<Enemy> enemy = new LinkedList<>();
    public LinkedList<Enemy_Bullet> enemy_bullets = new LinkedList<>();
    public LinkedList<PowerUp_LeftWeapon> leftWeapon = new LinkedList<>();
    public LinkedList<PowerUp_RightWeapon> rightWeapon = new LinkedList<>();
    String enemy_hit = "res\\shoot_now.wav";
    long timer = System.currentTimeMillis();
    private Sounds sounds = new Sounds();
    private ImageIcon enemyExplosion;
    private boolean enemyWas_Removed = false;
    private int enemyShot;
    private boolean noMore_enemies = false;
    private MainFrame mainFrame;
    private Enemy aux;

    public Controller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        enemyExplosion = mainFrame.getExplosion1();


    }

    public void updating() {

        whichLevel();


        //bullets//
        for (int i = 0; i < bullet.size(); i++) {
            Bullet b = bullet.get(i);

            if (b.getY() <= 0)
                bullet.remove(b);


            if (bullet.size() >= 1)
                enemyShot(i);


            b.updating();

            // System.out.println("Nr of bullets: " + bullet.size());
        }

        // leftWeapon //

        if (PowerUp.powerUp_activated) {


            for (int i = 0; i < leftWeapon.size(); i++) {

                PowerUp_LeftWeapon lw = leftWeapon.get(i);

                if (lw.getY() <= 0)
                    leftWeapon.remove(lw);

                if (leftWeapon.size() >= 1)
                    enemyShot_leftWeapon(i);


                lw.updating();

            }


            // Right Weapon //

            for (int i = 0; i < rightWeapon.size(); i++) {

                PowerUp_RightWeapon rw = rightWeapon.get(i);

                if (rw.getY() <= 0)
                    rightWeapon.remove(rw);

                if (rightWeapon.size() >= 1)
                    enemyShot_rightWeapon(i);


                rw.updating();

            }
        }

        // Dezactivating the powerUp //

        else if (!PowerUp.powerUp_activated) {
            for (int i = 0; i < rightWeapon.size(); i++) {

                PowerUp_RightWeapon rw = rightWeapon.get(i);

                rw.updating();

                if (rw.getY() <= -30)
                    rightWeapon.remove(rw);


            }

            for (int i = 0; i < leftWeapon.size(); i++) {

                PowerUp_LeftWeapon lw = leftWeapon.get(i);

                lw.updating();

                if (lw.getY() <= -30)
                    leftWeapon.remove(lw);

            }

        }


        //Enemy//
        for (int i = 0; i < enemy.size(); i++) {
            Enemy e = enemy.get(i);
            Enemy e2 = enemy.get(enemy.size() - 1);

            enemyShot = random_numberGenerator();

            if ((e.getY() >= enemyShot) && (e.getY() <= enemyShot + 2)) {
                enemy_bullets.add(new Enemy_Bullet(mainFrame, enemy.get(i).getX() + 45, enemy.get(i).getY() + 25));
                String enemyShot = "res\\enemyShot.wav";
                sounds.playsound(enemyShot, 0.5);
            }


            enemiesSpawn(e2);


            if (e.getY() >= 850) {
                e.setY(-50);
                e.setX((int) (Math.random() * 680));
            }

            e.updating();

        }


        for (int i = 0; i < enemy_bullets.size(); i++) {
            Enemy_Bullet eb = enemy_bullets.get(i);

            if (eb.getY() >= 850)
                enemy_bullets.remove(eb);

            eb.updating();
        }

    }

    public void draw(Graphics graphics) {

        //bullets//
        for (int i = 0; i < bullet.size(); i++) {
            Bullet b = bullet.get(i);
            b.draw(graphics);

        }


        // leftWeapon //
        for (int i = 0; i < leftWeapon.size(); i++) {
            PowerUp_LeftWeapon lw = leftWeapon.get(i);
            lw.draw(graphics);

        }


        // Right Weapon //
        for (int i = 0; i < rightWeapon.size(); i++) {
            PowerUp_RightWeapon rw = rightWeapon.get(i);
            rw.draw(graphics);
        }


        // enemy //
        for (int i = 0; i < enemy.size(); i++) {
            Enemy e = enemy.get(i);
            e.draw(graphics);


        }

        for (int i = 0; i < enemy_bullets.size(); i++) {

            Enemy_Bullet eb = enemy_bullets.get(i);

            eb.draw(graphics);
        }


    }

    public void enemyShot(int i) {
        int bX = bullet.get(i).getX();
        int bY = bullet.get(i).getY();

        for (int j = 0; j < enemy.size(); j++) {

            double eX = enemy.get(j).getX();
            double eY = enemy.get(j).getY();


            if (new Rectangle(bX, bY, 10, 30).
                    intersects(new Rectangle((int) eX, (int) eY, 95, 45))) {
                sounds.playsound(enemy_hit, 0.7);

                if (bullet.size() >= 1)
                    try {
                        bullet.remove(i);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Out of size for list ");
                    }
                enemy.get(j).setHealth(enemy.get(j).getHealth() - 1);

                if (enemy.get(j).getHealth() == 0) {
                    enemy.remove(j);

                    enemies_down++;
                    System.out.println(enemies_down);
                }


                if (enemy.size() == 0)
                    noMore_enemies = true;
                enemy_list_isEmpty();

            }


        }

    }

    public void enemyShot_leftWeapon(int i) {

        int lbX = leftWeapon.get(i).getX();
        int lbY = leftWeapon.get(i).getY();

        for (int j = 0; j < enemy.size(); j++) {

            double eX = enemy.get(j).getX();
            double eY = enemy.get(j).getY();


            if (new Rectangle(lbX, lbY, 10, 30).
                    intersects(new Rectangle((int) eX, (int) eY, 95, 45))) {
                sounds.playsound(enemy_hit, 0.7);

                if (leftWeapon.size() >= 1) {
                    try {
                        leftWeapon.remove(i);
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }


                enemy.get(j).setHealth(enemy.get(j).getHealth() - 1);

                if (enemy.get(j).getHealth() == 0) {
                    enemy.remove(j);
                    enemies_down++;
                    System.out.println(enemies_down);
                }


                if (enemy.size() == 0)
                    noMore_enemies = true;
                enemy_list_isEmpty();
            }

        }

    }

    public void enemyShot_rightWeapon(int i) {

        int rbX = rightWeapon.get(i).getX();
        int rbY = rightWeapon.get(i).getY();

        for (int j = 0; j < enemy.size(); j++) {

            double eX = enemy.get(j).getX();
            double eY = enemy.get(j).getY();


            if (new Rectangle(rbX, rbY, 10, 30).
                    intersects(new Rectangle((int) eX, (int) eY, 95, 45))) {
                sounds.playsound(enemy_hit, 0.7);

                if (rightWeapon.size() >= 1) {
                    try {
                        rightWeapon.remove(i);
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }


                enemy.get(j).setHealth(enemy.get(j).getHealth() - 1);

                if (enemy.get(j).getHealth() == 0) {
                    enemy.remove(j);
                    enemies_down++;
                    System.out.println(enemies_down);
                }


                if (enemy.size() == 0)
                    noMore_enemies = true;
                enemy_list_isEmpty();
            }

        }

    }

    public void enemy_list_isEmpty() {
        if (noMore_enemies) {
            if (level1) {
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                noMore_enemies = false;
            } else if (level2) {
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                noMore_enemies = false;
            } else if (level3) {
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                noMore_enemies = false;
            } else if (level4) {
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                noMore_enemies = false;
            } else if (level5) {
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));

                noMore_enemies = false;
            }
        }
    }

    public int random_numberGenerator() {
        return 5 + (int) (Math.random() * 601);
    }

    public void whichLevel() {

        if (enemies_down < 30) {
            level1 = true;
            level2 = false;
            level3 = false;
            level4 = false;
            level5 = false;
        } else if ((enemies_down >= 30) && (enemies_down < 70)) {

            level1 = false;
            level2 = true;

        } else if ((enemies_down >= 70) && (enemies_down < 110)) {
            level2 = false;
            level3 = true;
        } else if ((enemies_down >= 110) && (enemies_down < 160)) {
            level3 = false;
            level4 = true;
        } else if ((enemies_down >= 160) && (enemies_down < 210)) {
            level4 = false;
            level5 = true;
        }


    }


    public void enemiesSpawn(Enemy e) {
        if (level1) {
            if (e.getY() >= 200) {

                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));

            }
        }


        if (level2) {
            if (e.getY() >= 150) {

                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));

            }
        }


        if (level3) {
            if (e.getY() >= 150) {

                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
            }
        }


        if (level4) {
            if (e.getY() >= 150) {

                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
            }
        }


        if (level5) {
            if (e.getY() >= 100) {

                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
                enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));
            }
        }

    }

    public int getEnemies_down() {
        return enemies_down;
    }


}

