import ru.ifmo.se.pokemon.*;

/*
100% chance to decrease Def of Opponent by 2 stages.
 */

class AcidSpray extends SpecialMove {
    protected AcidSpray() {
        super(Type.POISON, 40.0, 100.0);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.DEFENSE, -2);
    }
    @Override
    protected String describe() {
        return("The target's Defense is lowering by two stages.");
    }
}