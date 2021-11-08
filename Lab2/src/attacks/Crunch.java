package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class Crunch is successor of PhysicalMove attack.
 * The target's Defense is lowering by one stage with chance 20%.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Crunch extends PhysicalMove {

    /** Constructor of this class */
    public Crunch() {
        super(Type.DARK, 80.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.2) {
            p.setMod(Stat.DEFENSE, -1);
        }
    }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return ("The target's Defense is lowering by one stage.");
    }
}