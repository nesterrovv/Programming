package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class SuperBite is successor of PhysicalMove attack.
 * It inflicts damage and has a 90% chance of causing the target to flinch.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class SuperBite extends PhysicalMove {

    /** Constructor of this class */
    public SuperBite() {
        super(Type.DARK, 60.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.9) {
            Effect.flinch(p);
        }
    }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return ("Flinched");
    }
}