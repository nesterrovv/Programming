package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class SludgeWave is successor of SpecialMove attack.
 * It deals damage and has a 10% chance of poisoning the target.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class SludgeWave extends SpecialMove {

    /** Constructor of this class */
    public SludgeWave() {
        super(Type.POISON, 95.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) {
            Effect.poison(p);
        }
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