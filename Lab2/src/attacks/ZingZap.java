package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class ZingZap is successor of PhysicalMove attack.
 * This attack has a 30% chance to make the target flinch.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class ZingZap extends PhysicalMove {

    /** Constructor of this class */
    public ZingZap() {
        super(Type.ELECTRIC, 80.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3) Effect.flinch(p);
    }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return("Flinched!");
    }
}
