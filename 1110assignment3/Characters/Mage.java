package Characters;

import Attacks.DamageSpell;
import Attacks.MeleeAttack;

import java.awt.*;

/**
 * This class represents the Mage has 4 attacks
 *
 * @author Zhaojie Wang - B00846957
 */
public class Mage extends Caster{

    //Constructor
    public Mage(String name, int intellect, int maxHP, Point position, int maxMana) {
        super(name, intellect, maxHP, position, maxMana);

        attackList.add(new MeleeAttack(0, "Staff", 3, 3));
        attackList.add(new DamageSpell(20, "Fire Ball", 10, 20));
        attackList.add(new DamageSpell(15, "Frost Ball", 7, 15));
        attackList.add(new DamageSpell(30, "Lightning", 15, 20));
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
        //Staff
        if(attackIndex == 0){
            if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) < 3){
                return target.getCurrentHP() - this.attackList.get(0).interactWithTarget(target, 0);
            }else{
                return -2;
            }
        //Fire Ball
        }else if(attackIndex == 1){
            if(this.getCurrentMana() < 20){
                return -3;
            }else{
                if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 20){
                    this.setCurrentMana(this.getCurrentMana() - 20);
                    return target.getCurrentHP() - this.attackList.get(1).interactWithTarget(target, intellect);
                }else{
                    return -2;
                }
            }
        //Frost Ball
        }else if(attackIndex == 2){
            if(this.getCurrentMana() < 15){
                return -3;
            }else{
                if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 15){
                    this.setCurrentMana(this.getCurrentMana() - 15);
                    return target.getCurrentHP() - this.attackList.get(2).interactWithTarget(target, intellect);
                }else{
                    return -2;
                }
            }
        //Lightning
        }else if(attackIndex == 3){
            if(this.getCurrentMana() < 30){
                return -3;
            }else{
                if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 20){
                    this.setCurrentMana(this.getCurrentMana() - 30);
                    return target.getCurrentHP() - this.attackList.get(2).interactWithTarget(target, intellect);
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
        return "0 - Attack - Staff (0) - Damage: 3\n" +
                "1 - DamageSpell - Fire Ball (20) - Damage: 10\n" +
                "2 - DamageSpell - Frost Ball (15) - Damage: 7\n" +
                "3 - DamageSpell - Lightning (30) - Damage: 15\n";
    }

}