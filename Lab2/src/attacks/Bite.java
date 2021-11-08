package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class Bite is successor of PhysicalMove attack.
 * Bite inflicts damage and has a 10% chance of causing the target to flinch.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Bite extends PhysicalMove {
    /** Constructor of this class */
    public Bite() {
        super(Type.DARK, 60.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) {
            Effect.flinch(p);
        }
    }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return("Flinched");
    }
}