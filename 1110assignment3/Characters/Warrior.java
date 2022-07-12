package Characters;

import Attacks.MeleeAttack;

import java.awt.*;

/**
 * This class represents the Warrior has 3 attacks
 *
 * @author Zhaojie Wang - B00846957
 */
public class Warrior extends Melee{

    //Constructor
    public Warrior(String name, int strenght, int maxHP, Point position, int maxEnergy) {
        super(name, strenght, maxHP, position, maxEnergy);

        attackList.add(new MeleeAttack(0, "Punch", 5, 3));
        attackList.add(new MeleeAttack(3, "Slam", 5, 3));
        attackList.add(new MeleeAttack(20, "Charge", 30, 15));
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
        //Punch
        if(attackIndex == 0){
            if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 3){
                return target.getCurrentHP() - this.attackList.get(0).interactWithTarget(target, strenght);
            }else{
                return -2;
            }
        //Slam
        }else if(attackIndex == 1){
            if(this.getCurrentEnergy() < 3){
                return -3;
            }else{
                if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 3){
                    this.setCurrentEnergy(this.getCurrentEnergy() - 3);
                    return target.getCurrentHP() - this.attackList.get(1).interactWithTarget(target, strenght);
                }else{
                    return -2;
                }
            }
        //Charge
        }else if(attackIndex == 2){
            if(this.getCurrentEnergy() < 20){
                return -3;
            }else{
                if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 15){
                    this.setCurrentEnergy(this.getCurrentEnergy() - 20);
                    return target.getCurrentHP() - this.attackList.get(2).interactWithTarget(target, strenght);
                }else{
                    return -2;
                }
            }
        //Wrong Attack
        }else{
            return -1;
        }
    }

    /**
     * each line contain the attack's index on the ArrayList and the attack's string representation
     * @return a string with each of the character's attacks on a new line
     */
    @Override
    public String getAttacks(){
        return "0 - Attack - Punch (0) - Damage: 5\n" +
                "1 - Attack - Slam (3) - Damage: 5\n" +
                "2 - Attack - Charge (20) - Damage: 30\n";
    }

}