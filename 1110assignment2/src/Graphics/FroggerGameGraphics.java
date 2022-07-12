package Graphics;

import GameLogic.Car;
import GameLogic.Game;

import javax.swing.*;
import java.awt.*;

/**
 * NOTE: Uncomment the code bellow after you implemented the assignment if you want to play the game
 *
 * Graphics representation of the Frogger Game.
 *
 * This class will Create a new Game object, add the cars to the game and run the Graphics "engine" (JFrame)
 * It will register a keyboard listener object so we can listen to keyboard events*
 *
 * Feel free to modify this if you want. This class is not going to evaluated by Mimir or the markers.
 *
 * MAKE SURE THAT YOUR CODE COMPILES (INCLUDING THIS CLASS) WHEN YOU SUBMIT
 *
 * @author Juliano Franz
 */
public class FroggerGameGraphics extends JPanel {
    private Game froggerGame;
    private static final Point START_POSITION = new Point(300,500);

    /**
     * Creates a new graphics element of the Frogger Game
     * It will add four cars, two in each direction and register the keyboard listener
     */
    public FroggerGameGraphics(){
        froggerGame = new Game("Hui", START_POSITION);

        KeyboardListener kbListener = new KeyboardListener(froggerGame);
        addKeyListener(kbListener);
        setFocusable(true);
    }

    /**
     * Game loop method used to move the cars, check if the player is dead/alive and if the win condition
     * @param gameFrame the JFrame window
     * @throws InterruptedException if the update time is interrupted for some reason
     */
    public void graphicsGameLoop(JFrame gameFrame) throws InterruptedException{
        boolean playerIsHit = false;
        boolean playerWon = false;
        while (!playerIsHit && !playerWon){
            froggerGame.moveCars(600);
            gameFrame.repaint();
            playerIsHit = froggerGame.isPlayerHit();
            playerWon = froggerGame.hasPlayerWon();
            Thread.sleep(50);

        }
        if (playerIsHit) {
            JOptionPane.showMessageDialog(this,"Smells like fried frog",
                    "you got hit!",JOptionPane.OK_OPTION);
        }
        else {
            JOptionPane.showMessageDialog(this,"No fried frog today",
                    "Safe!",JOptionPane.OK_OPTION);
        }

        System.exit(0);
    }

    /**
     * Overridden method used to paint the game elements to the screen.
     * We will talk abour Overriding methods once we learn about inheritance
     *
     * This method is implicitly called when we call the repaint method on the JFrame object
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        //Draw the "road median"
        g2d.setColor(Color.lightGray);
        g2d.fillRect(0,225,600,50);

        //Draw the "safe area"
        g2d.setColor(new Color(117,214,62));
        g2d.fillRect(0,0,600,50);

        //Draw the frog as a red circle
        g2d.setColor(Color.getColor("#006611"));
        g2d.fillOval(froggerGame.getPlayer().getCurrentPosition().x,
                froggerGame.getPlayer().getCurrentPosition().y,
                30, 30);

        //Draw each car using rectangles
        for (Car car: froggerGame.getCars()) {
            Point carPosition = car.getCurrentPosition();
            Color carColor = car.getBodyColour();
            g2d.setColor(carColor);
            g2d.fillRect(carPosition.x, carPosition.y, car.getXSize(), car.getYSize());
        }


    }
}
