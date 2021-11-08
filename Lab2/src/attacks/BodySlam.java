package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class BodySlam is successor of PhysicalMove attack.
 * It deals damage and has a 30% chance of paralyzing the target.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class BodySlam extends PhysicalMove {

    /** Constructor of this class */
    public BodySlam() {
        super(Type.NORMAL, 85.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3) {
            Effect.paralyze(p);
        }
    }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return("Paralyzed");
    }
}
