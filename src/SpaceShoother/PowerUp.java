package SpaceShoother;

import javax.swing.*;
import java.awt.*;

public class PowerUp {

    static boolean powerUp_activated = false;
    static long timer;
    String poweUpSound = "res\\powerUp.wav";
    long timer2;
    private Sounds sounds = new Sounds();
    private int x;
    private int y;
    private boolean da = false;
    private MainFrame mainFrame;
    private Player p;
    private ImageIcon powerUp;
    public PowerUp(MainFrame mainFrame, Player p, int x, int y) {
        this.x = x;
        this.y = y;
        this.mainFrame = mainFrame;
        this.p = p;

        powerUp = mainFrame.getPowerUp();
    }

    static public boolean isPowerUpWasActivated() {
        return powerUp_activated;
    }

    public void updating() {


        if ((System.currentTimeMillis() - timer > 8000) && (!powerUp_activated)) {
            da = true;
            timer += 8000;
        }

        if (powerUp_activated)
            timer = System.currentTimeMillis();

        if (da) {
            y += 2;

            if (y >= 850) {
                da = false;
                y = -60;
                x = (int) (Math.random() * 680);
            } else if (powerUp_activated) {
                y = -60;
                x = (int) (Math.random() * 680);

                da = false;
            }
        }


        if ((System.currentTimeMillis() - timer2 > 6000) && (powerUp_activated)) {
            powerUp_activated = false;
        }

        powerUp_Taken();
    }

    public void draw(Graphics graphics) {
        powerUp.paintIcon(mainFrame, graphics, x, y);
        //graphics.fillRect(x, y, 50, 50);
        // graphics.setColor(Color.red);
    }

    public void powerUp_Taken() {


        double pX = p.getX();
        double pY = p.getY();


        if (new Rectangle(x, y, 50, 50).
                intersects(new Rectangle((int) pX, (int) pY + 35, 95, 35))) {
            System.out.println("Power Up taken");
            sounds.playsound(poweUpSound, 1.5);


            powerUp_activated = true;

            timer2 = System.currentTimeMillis();


        }


    }

    public boolean isDa() {
        return da;
    }

    public void setTimer(long timer) {
        PowerUp.timer = timer;
    }
}
