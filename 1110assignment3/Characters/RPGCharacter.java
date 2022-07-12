package Characters;

import Attacks.Attack;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the RPG character
 *
 * @author Zhaojie Wang - B00846957
 */
public abstract class RPGCharacter implements Comparable<RPGCharacter>{

    private String name;
    private int maxHP;
    private Point position;
    protected int currentHP;
    protected int intellect;
    protected int strenght;
    protected ArrayList<Attack> attackList;

    //Constructor
    public RPGCharacter(String name, int intellect, int strenght, int maxHP, Point position){
        this.name = name;
        this.intellect = intellect;
        this.strenght = strenght;
        this.maxHP = maxHP;
        this.position = position;
        this.currentHP = maxHP;
        attackList = new ArrayList<>();
    }

    //Getters
    public int getCurrentHP(){
        return currentHP;
    }
    public Point getPosition(){
        return position;
    }
    public int getMaxHP(){
        return maxHP;
    }
    public String getName(){
        return name;
    }

    /**
     * The move method will move the character by calling the translate method from the Point class
     * @param x move the character's current x position
     * @param y move the character's current y position
     */
    public void move(int x, int y){
        position.translate(x, y);
    }

    /**
     * Deals damage to the character by deducting parameters to the character's current HP
     * If the HP falls below zero (included), the method should set the currentHP to zero
     * @param damageHP an int that subtracts from the current character's HP
     * @return return false if the character is dead (currentHP is zero)
     */
    public boolean takeDamage(int damageHP){
        if(this.currentHP > damageHP){
            this.currentHP = this.getCurrentHP() - damageHP;
            return true;
        }else{
            this.currentHP = 0;
            return false;
        }
    }

    /**
     * Heals the character by adding the parameter to the character's current HP
     * A character cannot have more HP than the maxHP value
     * @param healHP add an int from the current character's HP
     * @return return true if the character is fully healed
     */
    public boolean heal(int healHP){
        if(this.currentHP + healHP >= this.maxHP){
            this.currentHP = maxHP;
            return true;
        }else{
            this.currentHP = this.getCurrentHP() + healHP;
            return false;
        }
    }

    /**
     * an abstract attack method
     * @param target
     * @param attackIndex
     * @return int
     */
    public abstract int attack(RPGCharacter target, int attackIndex);

    /**
     * methods that implement the compare interface
     * compare two RPGCharacters based on their currentHP
     * @param compareCharacter object used to compare with the current object
     * @return order characters from the one with the highest currentHP to the lowest
     */
    @Override
    public int compareTo(RPGCharacter compareCharacter) {
        if(this.currentHP > compareCharacter.currentHP){
            return -1;
        }else if(this.currentHP < compareCharacter.currentHP){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * each line contain the attack's index on the ArrayList and the attack's string representation
     * @return a string with each of the character's attacks on a new line
     */
    public String getAttacks(){
        return "";
    }

    @Override
    public String toString(){
        return name + " (" + this.getClass().getSimpleName() + ") - " + currentHP + "/" + maxHP;
    }

}