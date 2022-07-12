package Characters;

import Attacks.DamageSpell;
import Attacks.HealingSpell;
import Attacks.MeleeAttack;
import Attacks.Resurrection;

import java.awt.*;

/**
 * This class represents the Priest has 4 attacks
 *
 * @author Zhaojie Wang - B00846957
 */
public class Priest extends Caster{

    //Constructor
    public Priest(String name, int intellect, int maxHP, Point position, int maxMana) {
        super(name, intellect, maxHP, position, maxMana);

        attackList.add(new MeleeAttack(0, "Wand", 3, 3));
        attackList.add(new DamageSpell(10, "Smite", 10, 7));
        attackList.add(new HealingSpell(20, "Flash Heal", 15, 15));
        attackList.add(new Resurrection());
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
        //Wand
        if(attackIndex == 0){
            if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 3){
                return target.getCurrentHP() - this.attackList.get(0).interactWithTarget(target, 0);
            }else{
                return -2;
            }
        //Smite
        }else if(attackIndex == 1){
            if(this.getCurrentMana() < 10){
                return -3;
            }else{
                if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 7){
                    this.setCurrentMana(this.getCurrentMana() - 10);
                    return target.getCurrentHP() - this.attackList.get(1).interactWithTarget(target, intellect);
                }else{
                    return -2;
                }
            }
        //Flash Heal
        }else if(attackIndex == 2){
            if(this.getCurrentMana() < 20){
                return -3;
            }else{
                if(target.getPosition().distance(this.getPosition().getX(), this.getPosition().getY()) <= 15){
                    this.setCurrentMana(this.getCurrentMana() - 20);
                    this.currentHP = this.currentHP + this.attackList.get(2).interactWithTarget(this, intellect);
                    return target.getCurrentHP();
                }else{
                    return -2;
                }
            }
        //Resurrection
        }else if(attackIndex == 3){
            if(this.getCurrentMana() < 50){
                return -3;
            }else{
                target.currentHP = target.getMaxHP();
                return target.currentHP;
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
        return "0 - Attack - Wand (0) - Damage: 3\n" +
                "1 - DamageSpell - Smite (10) - Damage: 10\n" +
                "2 - HealingSpell - Flash Heal (20) - Heal: 15\n" +
                "3 - Resurrection - Resurrection (50)\n";
    }

}