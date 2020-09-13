package SpaceShoother;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class MainFrame extends Canvas implements Runnable {
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static STATE state = STATE.MENU;
    public long gta = System.currentTimeMillis();
    WritingStuff writingStuff;
    GameOver gameOver;
    private boolean isRunning = false;
    private Thread thread;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private ImageIcon background = new ImageIcon("res\\Background.png");
    private ImageIcon player = new ImageIcon("res\\player.png");
    private ImageIcon bull = new ImageIcon("res\\laserRed.png");
    private ImageIcon playerRight = new ImageIcon("res\\playerRight.png");
    private ImageIcon playerLeft = new ImageIcon("res\\playerLeft.png");
    private ImageIcon enemyShip = new ImageIcon("res\\enemyShip.png");
    private ImageIcon playerDamaged = new ImageIcon("res\\playerDamaged.png");
    private ImageIcon enemyBullet = new ImageIcon("res\\laserGreen.png");
    private ImageIcon powerUp = new ImageIcon("res\\powerUp.png");
    private ImageIcon shield = new ImageIcon("res\\shield.png");
    private ImageIcon speedLine = new ImageIcon("res\\speedLine.png");
    private ImageIcon starBig = new ImageIcon("res\\starBig.png");

    private ImageIcon gtaZ = new ImageIcon("res\\gta.png");
    private ImageIcon explosion1 = new ImageIcon("res\\i_0001.png");
    private Player P;
    private Bullet bullet;
    private Enemy enemy;
    private Enemy_Bullet enemy_bullet;
    private Controller controller;
    private PowerUp pu;
    private MainMenu mainMenu = new MainMenu();
    private Background_loop background_loop;
    private LinkedList<Background_loop> bl = new LinkedList();
    private Sounds sounds = new Sounds();

    public static void main(String[] args) {

        String firstMusic_path = "res\\Trance - I'm Blue Remix 2012.wav";

        String second_music = "res\\secondsong.wav";
        Sounds sounds = new Sounds();
        sounds.playMusic(second_music, 0.6);


        MainFrame mainFrame = new MainFrame();

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 792, 798);
        frame.setTitle("Space Shooter");
        frame.add(mainFrame);

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        mainFrame.start();

    }

    private synchronized void start() {
        if (isRunning)
            return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    private synchronized void stop() throws InterruptedException {
        if (!isRunning)
            return;

        isRunning = false;
        thread.join();
        System.exit(1);

    }


    public void run() {


        long timer = System.currentTimeMillis();


        initialise();


        while (true) {

            if (isRunning) {
                updating();

                draw();

            }

            if (!isRunning) {
                writingStuff.draw_restart_the_game(getGraphics());
                newGame();
            }


            // if(!isRunning)
            // {
            //gameOver.newGame();
            //   newGame();

            // }


        }
        // try {
        //      stop();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        //  }
    }

    //////////////////////////

    public void initialise() {
        requestFocus();

        addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());


        // bullet=new Bullet(this,200,200);
        // enemy=new Enemy(this,200,200);

        bl.add(new Background_loop(this, 0));
        //bl.add(new Background_loop(this,-700));
        //bl.add(new Background_loop(this,-1400));


        controller = new Controller(this);


        P = new Player(this, controller, 200, 200);

        pu = new PowerUp(this, P, (int) (Math.random() * 680), -60);

        writingStuff = new WritingStuff(controller);


        gameOver = new GameOver(this, P, background_loop, pu, controller, writingStuff);


        controller.enemy.add(new Enemy(this, (int) (Math.random() * 680), -50));
        //  controller.enemy_bullets.add(new Enemy_Bullet(this,
        //controller.enemy.get(0).getX()+45,controller.enemy.get(0).getY()+25));


    }

    public void updating() {

        if (state == STATE.GAME) {
            for (int i = 0; i < bl.size(); i++) {
                Background_loop bbll = bl.get(i);
                Background_loop bl_last = bl.get(bl.size() - 1);


                if (bl_last.getY() >= -200) {
                    bl.add(new Background_loop(this, -798));
                }

                bbll.updating();

                if (bbll.getY() >= 798)
                    bl.remove(bbll);


            }


            P.updating();
            pu.updating();
            controller.updating();
        }

    }

    public void draw() {


        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }


        Graphics g = bs.getDrawGraphics();


        g.drawImage(image, 0, 0, WIDTH, HEIGHT, this);


        if (state == STATE.GAME) {

            for (int i = 0; i < bl.size(); i++) {
                Background_loop bbll = bl.get(i);
                bbll.draw(g);
            }


            P.draw(g);
            pu.draw(g);
            controller.draw(g);
            writingStuff.draw(g);
        } else if (state == STATE.MENU) {
            for (int i = 0; i < bl.size(); i++) {
                Background_loop bbll = bl.get(i);
                bbll.draw(g);

                g.setColor(Color.black);
                g.fillRect(0, 1, 800, 5);
                g.fillRect(0, 1, 5, 800);
                g.fillRect(773, 1, 5, 800);
                g.fillRect(1, 756, 800, 5);

                if (controller.getEnemies_down() >= 1) {
                    writingStuff.drawScore_AfterGameEnds(g);
                }

            }
            // if (System.currentTimeMillis()-gta<10000) {
            //    g.setColor(Color.black);
            //  g.fillRect(0, 0, 792, 798);
            // gtaZ.paintIcon(this, g, 100, -100);
            //}

            //   else
            mainMenu.draw(g);
        }


        g.dispose();
        bs.show();


    }

    /////////////////////////////////


    public void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();


        if (state == STATE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {

                P.setMovement_speedX(3);
            } else if (key == KeyEvent.VK_LEFT) {

                P.setMovement_speedX(-3);
            } else if (key == KeyEvent.VK_UP) {
                P.setMovement_speedY(-3);
            } else if (key == KeyEvent.VK_DOWN) {
                P.setMovement_speedY(3);
            }

            if (key == KeyEvent.VK_SPACE) {
                String shootPath = "res\\shoot.wav";

                controller.bullet.add(new Bullet(this, P.getX() + 45, P.getY() - 25));
                P.setY(P.getY() + 10);

                P.setNewBullet(true);
                P.start = System.currentTimeMillis();
                P.once = true;

                sounds.playsound(shootPath, 0.7);

                if (PowerUp.powerUp_activated) {
                    controller.leftWeapon.add(new PowerUp_LeftWeapon(this, P.getX() - 1, P.getY() + 18));

                    controller.rightWeapon.add(new PowerUp_RightWeapon(this, P.getX() + 90, P.getY() + 18));

                }
            }

            // if(!isRunning)
            if (key == KeyEvent.VK_ENTER) {
                isRunning = true;

            }

        }
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {

            P.setMovement_speedX(0);
        } else if (key == KeyEvent.VK_LEFT) {

            P.setMovement_speedX(0);
        } else if (key == KeyEvent.VK_UP) {
            P.setMovement_speedY(0);
        } else if (key == KeyEvent.VK_DOWN) {
            P.setMovement_speedY(0);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public ImageIcon getPlayerImageIcon() {
        return player;
    }

    public ImageIcon getPlayerRightImageIcon() {
        return playerRight;
    }

    public ImageIcon getPlayerLeft() {
        return playerLeft;
    }

    public ImageIcon getBulletImageIcon() {
        return bull;
    }

    public ImageIcon getEnemyShip() {
        return enemyShip;
    }

    public ImageIcon getPlayerDamaged() {
        return playerDamaged;
    }

    public ImageIcon getEnemyBullet() {
        return enemyBullet;
    }

    public ImageIcon getPowerUp() {
        return powerUp;
    }

    public ImageIcon getShield() {
        return shield;
    }

    public ImageIcon getSpeedLine() {
        return speedLine;
    }

    public ImageIcon getStarBig() {
        return starBig;
    }

    public ImageIcon getBackg() {
        return background;
    }

    public ImageIcon getExplosion1() {
        return explosion1;
    }

    public void newGame() {


        controller = new Controller(this);
        P = new Player(this, controller, 200, 200);

        pu = new PowerUp(this, P, (int) (Math.random() * 680), -60);


        controller.enemy.add(new Enemy(this, (int) (Math.random() * 680), -50));
        controller.enemy.get(0).setHealth(1);

        writingStuff = new WritingStuff(controller);

    }

    public enum STATE {
        MENU,
        GAME
    }


}
