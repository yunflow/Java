package Graphics;

import javax.swing.*;
import java.awt.*;

/**
 * NOTE: Uncomment the code bellow after you implemented the assignment if you want to play the game
 * MAKE SURE TEH CODE IS COMPILING WHEN YOU SUBMIT. YOU CAN ALWAYS UNDO THE CHANGES IN THESE FILES
 *
 * Runner class for the Frogger game.
 *
 * @author Juliano Franz
 */
public class FroggerGameRunner extends JPanel {

    private FroggerGameGraphics chickenGame;
    private static final Point START_POSITION = new Point(300,500);

    public static void main(String[] args) throws InterruptedException {

        JFrame gameFrame = new JFrame("The Frogger Game");
        FroggerGameGraphics runner = new FroggerGameGraphics();
        gameFrame.add(runner);
        gameFrame.setSize(600,600);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runner.graphicsGameLoop(gameFrame);

    }

}
