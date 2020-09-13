package SpaceShoother;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameOver extends KeyAdapter {

    public boolean gameIsOver = false;
    private MainFrame mainFrame;
    private Player player;
    private Background_loop background_loop;
    private PowerUp powerUp;
    private Controller controller;
    private WritingStuff writingStuff;

    public GameOver(MainFrame mainFrame, Player player, Background_loop background_loop, PowerUp powerUp, Controller controller,
                    WritingStuff writingStuff) {

        this.mainFrame = mainFrame;
        this.player = player;
        this.background_loop = background_loop;
        this.powerUp = powerUp;
        this.controller = controller;
        this.writingStuff = writingStuff;


    }


    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // if(key==KeyEvent.VK_ENTER)
        //  {
        newGame();
        // }
    }


    public void newGame() {
        controller = new Controller(mainFrame);
        player = new Player(mainFrame, controller, 200, 200);

        powerUp = new PowerUp(mainFrame, player, (int) (Math.random() * 680), -60);

        controller.enemy.add(new Enemy(mainFrame, (int) (Math.random() * 680), -50));

        writingStuff = new WritingStuff(controller);

        mainFrame.setRunning(true);

    }


    public void game_Over() {
        if (gameIsOver) {

        }


    }
}
