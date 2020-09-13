package SpaceShoother;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {


    /*
     public Rectangle play=new Rectangle(250,250,200,60);
    public Rectangle instructions=new Rectangle(250,350,200,60);
    public Rectangle quit=new Rectangle(250,450,200,60);
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();

        if ((mX >= 250) && (mX <= 450))
            if ((mY >= 250) && (mY <= 310)) {
                MainFrame.state = MainFrame.STATE.GAME;
                PowerUp.timer = System.currentTimeMillis();
                Player.invincibleTime = true;
                Player.timer = System.currentTimeMillis();
                Controller.enemies_down = 0;
            }

        if ((mX >= 250) && (mX <= 450))
            if ((mY >= 450) && (mY <= 510)) {
                System.exit(1);
            }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
