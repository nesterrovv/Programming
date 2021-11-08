package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class PinMissile is successor of PhysicalMove attack.
 * Pin Missile hits 2-5 times per turn used.
 * The probability of each interval and total power after each hit:
 * Hits 	Prob.	Power
 * 2	    3⁄8	    50
 * 3	    3⁄8	    75
 * 4	    1⁄8	    100
 * 5	    1⁄8	    125
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class PinMissile extends PhysicalMove {
    public PinMissile() {
        super(Type.BUG, 25.0, 95.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     * @param damage this attack damage
     */
    @Override
    public void applyOppDamage(Pokemon p, double damage) {
        double chance = Math.random() * 100;
        int counter = 1;
        if (chance <= 37.5) {
            damage = 50;
            counter = 2;
        }
        if (chance > 37.5 && chance <= 75.0) {
            damage = 75.0;
            counter = 3;
        }
        if (chance > 75.0 && chance <= 87.5) {
            damage = 100.0;
            counter = 4;
        }
        if (chance > 87.5) {
            damage = 125.0;
            counter = 5;
        }
        while (counter != 0) {
            p.setMod(Stat.HP, (int) Math.round(damage));
            counter --;
        }
    }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return ("Random effect");
    }
}