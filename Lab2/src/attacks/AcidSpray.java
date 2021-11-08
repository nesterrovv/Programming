package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class AcidSpray is successor of SpecialMove attack.
 * Has 100% chance to decrease defence of opponent by 2 stages.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class AcidSpray extends SpecialMove {
    /** Constructor of this class */
    public AcidSpray() {
        super(Type.POISON, 40.0, 100.0);
    }

    /**
     * Method which allows to use this attack effect.
     * @param p is attacked opponent.
     */
    @Override
    public void applyOppEffects(Pokemon p) {
        p.setMod(Stat.DEFENSE, -2);
    }

    /**
     * Method which overwrites description of this attack.
     * @return new description of attack.
     */
    @Override
    public String describe() {
        return("The target's Defense is lowering by two stages.");
    }
}