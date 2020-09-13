package SpaceShoother;

import java.awt.*;

public class WritingStuff {

    Controller controller;

    public WritingStuff(Controller controller) {
        this.controller = controller;
    }

    public void draw(Graphics graphics) {

        String convToString = String.valueOf(controller.getEnemies_down());
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("serif", Font.BOLD, 25));


        if (Controller.level1)
            graphics.drawString("LEVEL 1", 650, 20);

        else if (Controller.level2)
            graphics.drawString("LEVEL 2", 650, 20);

        else if (Controller.level3)
            graphics.drawString("LEVEL 3", 650, 20);

        else if (Controller.level4)
            graphics.drawString("LEVEL 4", 650, 20);

        else if (Controller.level5)
            graphics.drawString("LEVEL 5", 650, 20);

        graphics.drawString("Enemies Killed: " + convToString, 0, 20);

    }

    public void draw_restart_the_game(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("serif", Font.BOLD, 50));
        graphics.drawString("PRESS ENTER TO RESTART", 50, 350);

    }

    public void drawScore_AfterGameEnds(Graphics graphics) {

        String convToString = String.valueOf(controller.getEnemies_down());

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("serif", Font.BOLD, 25));
        graphics.drawString("Enemies Killed: ", 260, 180);
        graphics.drawString(convToString, 335, 210);
    }

}
