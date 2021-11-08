package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class MudSlap is successor of SpecialMove attack.
 * Mud-Slap inflicts damage and has a 99.6% chance of
 * lowering the target's accuracy stat by one stage.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class MudSlap extends SpecialMove {

    /** Constructor of this class */
    public MudSlap() {
        super(Type.GROUND, 20.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.996) {
            p.setMod(Stat.ACCURACY, -1);
        }
    }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return ("The target's Accuracy is lowering by one stage.");
    }
}
