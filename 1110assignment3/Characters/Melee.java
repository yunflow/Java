package Characters;

import java.awt.*;

/**
 * This class represents the Melee
 * Melees are characters who deal damage by physical attacks primarily
 *
 * @author Zhaojie Wang - B00846957
 */
public abstract class Melee extends RPGCharacter{

    private int maxEnergy;
    private int currentEnergy;

    //Constructor
    public Melee(String name, int strenght, int maxHP, Point position, int maxEnergy) {
        super(name, 1, strenght, maxHP, position);
        this.maxEnergy = maxEnergy;
        currentEnergy = this.maxEnergy;
    }

    //Getter for currentEnergy
    public int getCurrentEnergy(){
        return currentEnergy;
    }
    //Setter for currentEnergy
    public void setCurrentEnergy(int currentEnergy){
        this.currentEnergy = currentEnergy;
    }

    /**
     * this method will select the attack from the ArrayList and call the interactWithTarget method
     * to inflict damage, deduct the energy cost
     * @param target an RPG object is used to be attacked
     * @param attackIndex which attack skill to choose
     * @return return a negative number in case the attack fails:
     *             -1 attackIndex not in range of the ArrayList
     *             -2 target out of range
     *             -3 not enough energy
     *         return the target's currentHP if the attack is successful
     */
    @Override
    public int attack(RPGCharacter target, int attackIndex){
        return -1;
    }

    @Override
    public String toString(){
        return this.getName() + " (" + this.getClass().getSimpleName() + ") - " + this.getCurrentHP() + "/" +
                this.getMaxHP() + "\n" + "Energy " + currentEnergy + "/" + maxEnergy;
    }

}