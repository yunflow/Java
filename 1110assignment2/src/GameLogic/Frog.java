package GameLogic;

import java.awt.*;
/**
 * This is Frog class
 * @author Zhaojie Wang - B00846957
 */
public class Frog {
    private String name;
    private Point currentPosition;

    //Constructor 1
    public Frog(String name, Point currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }
    //Constructor 2
    public Frog(String name) {
        this.name = name;
        //set the position(0,0)
        currentPosition = new Point(0,0);
    }

    /**
     * Getter for the name of the frog.
     * @return The frog's name as String.
     */
    public String getName() {
        return name;
    }
    /**
     * Getter for the current frog's position.
     * @return The frog's current position as Point.
     */
    public Point getCurrentPosition() {
        return currentPosition;
    }

    /**
     * translates the frog given an x and y translations, respectively
     * @param x move the player's current x position.
     * @param y move the player's current y position.
     * @return new coordinate points for the player's frog.
     */
    public Point move(int x,int y){
        currentPosition.setLocation(currentPosition.getX() + x, currentPosition.getY() + y);
        return currentPosition;
    }

    /**
     * Computes the distance between the frog object and the point received as an argument.
     * @param point Place a point that needs to be compared for distance.
     * @return Calculated distance about the newly placed point and the point of the player frog as a double.
     */
    public double distanceFromPoint(Point point){
        double pointX = point.getX();
        double pointY = point.getY();
        double currentPositionX = currentPosition.getX();
        double currentPositionY = currentPosition.getY();
        double distance = Math.sqrt(((pointX - currentPositionX) * (pointX - currentPositionX))
                            + ((pointY - currentPositionY) * (pointY - currentPositionY)));
        return distance;
    }

    /**
     * Returns the bounds of the car object which can be used to detect collision
     * @return A Rectangle object defining the bounds of the frog
     */
    public Rectangle getCollisionBounds(){
        return new Rectangle(currentPosition.x,currentPosition.y,30,30);
    }

    /**
     * toString Override
     * @return A string representation of the frog
     */
    public String toString(){
        return "The frog named " + name + " is at (" + (int)currentPosition.getX() + "," + (int)currentPosition.getY() + ")";
    }
}