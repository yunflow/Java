package Characters;

import java.awt.*;

/**
 * This class represents the Caster
 * Casters are characters who deal damage by casting spells primarily
 *
 * @author Zhaojie Wang - B00846957
 */
public abstract class Caster extends RPGCharacter{

    private int maxMana;
    private int currentMana;

    //Constructor
    public Caster(String name, int intellect, int maxHP, Point position, int maxMana) {
        super(name, intellect, 1, maxHP, position);
        this.maxMana = maxMana;
        currentMana = this.maxMana;
    }

    //Getter for currentMana
    public int getCurrentMana(){
        return currentMana;
    }
    //Setter for currentMana
    public void setCurrentMana(int currentMana){
        this.currentMana = currentMana;
    }

    /**
     * this method will select the attack from the ArrayList and call the interactWithTarget method
     * to inflict damage or heal the target
     * @param target an RPG object is used to be attacked
     * @param attackIndex which attack skill to choose
     * @return return a negative number in case the attack fails:
     *             -1 attackIndex not in range of the ArrayList
     *             -2 target out of range
     *             -3 not enough mana
     *         return the target's currentHP if the attack is successful
     */
    @Override
    public int attack(RPGCharacter target, int attackIndex){
        return -1;
    }

    @Override
    public String toString(){
        return this.getName() + " (" + this.getClass().getSimpleName() + ") - " + this.getCurrentHP() + "/" +
                this.getMaxHP() + "\n" + "Mana " + currentMana + "/" + maxMana;
    }

}