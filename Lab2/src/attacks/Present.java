package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class Present is successor of PhysicalMove attack.
 * Has 100% chance to decrease defence of opponent by 2 stages.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Present extends PhysicalMove {

    /** Constructor of this class */
    public Present () {
        super(Type.NORMAL, 1.0, 90.0);
    }

    /**
     * Method which allows to use this attack effect.
     * This attack randomly does one of the following:
     * (20% chance) - Heals the target by 1/4 of its total HP.
     * (40% chance) - This attack's base power is 40.
     * (30% chance) - This attack's base power is 80.
     * (10% chance) - This attack's base power is 120.
     * @param p is attacked opponent.
     * @param damage this attack damage.
     */
    @Override
    public void applyOppDamage(Pokemon p, double damage) {
        double chance = Math.random() * 100;
        if (chance <= 40) {
            damage  = 40.0;
        }
        if (chance > 40 && chance <= 70) {
            damage  = 80.0;
        }
        if (chance > 40 && chance <= 90) {
            damage  = 120.0;
        }
        if (chance > 90) {
            damage = p.getHP() * 1.25;
        }
        p.setMod(Stat.HP, (int) Math.round(damage));
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



