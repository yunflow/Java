package GameLogic;

import java.awt.*;
/**
 * This is Car class
 * @author Zhaojie Wang - B00846957
 */
public class Car{
    private Point currentPosition;
    private Color bodyColour;
    private int movementSpeed;
    private int xSize;
    private int ySize;
    private boolean moveLeft;

    //Constructor
    public Car(Point currentPosition, Color bodyColour, int movementSpeed, int xSize, int ySize, boolean moveLeft) {
        this.currentPosition = currentPosition;
        this.bodyColour = bodyColour;
        this.movementSpeed = movementSpeed;
        this.xSize = xSize;
        this.ySize = ySize;
        this.moveLeft = moveLeft;
    }

    /**
     * Getter for the current position of the car.
     * @return The car's current position as Point.
     */
    public Point getCurrentPosition() {
        return currentPosition;
    }
    /**
     * Getter for the current color of the car.
     * @return The car's body color.
     */
    public Color getBodyColour() {
        return bodyColour;
    }
    /**
     * Getter for the movement speed of the car.
     * @return The car's movement speed as an int.
     */
    public int getMovementSpeed() {
        return movementSpeed;
    }
    /**
     * Getter for the car's X size.
     * @return The car's length as an int.
     */
    public int getXSize() {
        return xSize;
    }
    /**
     * Getter for the car's Y size.
     * @return The car's width as an int.
     */
    public int getYSize() {
        return ySize;
    }
    /**
     * by default cars move from right to left.
     * @return return true when car should move from right to left.
     */
    public boolean isMoveLeft() {
        return moveLeft;
    }

    /**
     * moves the car in the X direction using movementSpeed as the step/value.
     * It takes into account the moveLeft variable to define if it should move from left to right or right to left.
     */
    public void move(){
        if(this.isMoveLeft() == true){
            currentPosition.setLocation(currentPosition.getX() - this.getMovementSpeed(),currentPosition.getY());
        }else if(this.isMoveLeft() == false){
            currentPosition.setLocation(currentPosition.getX() + this.getMovementSpeed(),currentPosition.getY());
        }
    }

    /**
     * Returns the bounds of the car object which can be used to detect collision
     * @return A Rectangle object defining the bounds of the car
     */
    public Rectangle getCollisionBounds(){
        return new Rectangle(currentPosition.x, currentPosition.y,xSize,ySize);
    }
}