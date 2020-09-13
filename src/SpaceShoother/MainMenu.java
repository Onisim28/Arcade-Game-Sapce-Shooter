package SpaceShoother;

import java.awt.*;

public class MainMenu {

    public Rectangle play = new Rectangle(250, 250, 200, 60);
    public Rectangle instructions = new Rectangle(250, 350, 200, 60);
    public Rectangle quit = new Rectangle(250, 450, 200, 60);


    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;


        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("serif", Font.BOLD, 55));
        graphics.drawString("SPACE SHOOTHER", 100, 90);


        graphics.setColor(Color.white);
        graphics.setFont(new Font("serif", Font.BOLD, 55));
        graphics.drawString("PLAY", 270, 297);

        graphics2D.setColor(Color.black);
        graphics2D.draw(play);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("serif", Font.BOLD, 55));
        graphics.drawString("HELP", 270, 397);

        graphics2D.setColor(Color.black);
        graphics2D.draw(quit);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("serif", Font.BOLD, 55));
        graphics.drawString("QUIT", 270, 497);

        graphics2D.setColor(Color.black);
        graphics2D.draw(instructions);


    }
}
