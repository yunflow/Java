package GameLogic;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/**
 * This is Game class
 * @author Zhaojie Wang - B00846957
 */
public class Game{
    private Frog player;
    private ArrayList<Car> cars;

    //Constructor
    public Game(String name, Point point){
        player = new Frog(name, point);

        //creat 4 cars and add them to arraylist.
        Car car1 = new Car(new Point(100,400),Color.black, 10, 120,75, false);
        Car car2 = new Car(new Point(0,300),Color.blue, 30, 200,75, false);
        Car car3 = new Car(new Point(100,150),Color.black, 10, 120,75, true);
        Car car4 = new Car(new Point(0,80),Color.black, 20, 120,75, true);
        cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
    }

    /**
     * Getter for the current player.
     * @return The current player as Frog.
     */
    public Frog getPlayer() {
        return player;
    }
    /**
     * Getter for the current game's cars.
     * @return The game's current cars as ArrayList<Car>.
     */
    public ArrayList<Car> getCars() {
        return cars;
    }

    /**
     * Responds to keyboard events by moving the player int in the x or y direction.
     * We will be using the directional keys for our game.
     * @param keys int represents keys from the keyboard
     * @param pixels int represents the step (in pixels) of how much the player is going to move at each keypress
     */
    public void movePlayer(int keys, int pixels){
        if(keys == KeyEvent.VK_UP){
            player.move(0, -pixels);
        }else if(keys == KeyEvent.VK_DOWN){
            player.move(0, pixels);
        }else if(keys == KeyEvent.VK_LEFT){
            player.move(-pixels, 0);
        }else if(keys == KeyEvent.VK_RIGHT){
            player.move(pixels, 0);
        }
    }

    /**
     * Moves every car in the game by calling their move method.
     * If a car moves left to right goes past the int value, move the car back to zero.
     * If a car that moves right to left, reaches the 0 coordinate (left edge), move it back to the right edge.
     * @param rightEdge an int representing the edge of the game area
     */
    public void moveCars(int rightEdge){
        cars.get(0).move();
        if(cars.get(0).getCurrentPosition().getX() >= rightEdge){
            cars.get(0).getCurrentPosition().setLocation(0,cars.get(0).getCurrentPosition().getY());
        }
        cars.get(1).move();
        if(cars.get(1).getCurrentPosition().getX() >= rightEdge){
            cars.get(1).getCurrentPosition().setLocation(0,cars.get(1).getCurrentPosition().getY());
        }
        cars.get(2).move();
        if(cars.get(2).getCurrentPosition().getX() <= 0){
            cars.get(2).getCurrentPosition().setLocation(rightEdge,cars.get(2).getCurrentPosition().getY());
        }
        cars.get(3).move();
        if(cars.get(3).getCurrentPosition().getX() <= 0){
            cars.get(3).getCurrentPosition().setLocation(rightEdge,cars.get(3).getCurrentPosition().getY());
        }
    }

    /**
     * Determine if the player's coordinate point coincides with the car's coordinate point.
     * @return true if the player was hit by a car
     */
    public boolean isPlayerHit(){
        if(cars.get(0).getCollisionBounds().contains(player.getCurrentPosition())){
            return true;
        }else if(cars.get(1).getCollisionBounds().contains(player.getCurrentPosition())){
            return true;
        }else if(cars.get(2).getCollisionBounds().contains(player.getCurrentPosition())){
            return true;
        }else if(cars.get(3).getCollisionBounds().contains(player.getCurrentPosition())){
            return true;
        }
        return false;
    }

    /**
     * The player wins the game if the y coordinate of the frog is less than 20, included.
     * @return true if the player won the game
     */
    public boolean hasPlayerWon(){
        if(player.getCurrentPosition().getY() <= 20){
            return true;
        }
        return false;
    }
}