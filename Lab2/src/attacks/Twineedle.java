package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class Twineedle is successor of PhysicalMove attack.
 * It deals damage and will strike twice with 25 base power each time.
 * Twineedle has a 20% chance of poisoning the target.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Twineedle extends PhysicalMove {

    /** Constructor of this class */
    public Twineedle() {
        super(Type.BUG, 25.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.2) Effect.poison(p);
        }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return ("Poisoned");
    }
}