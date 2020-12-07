import ru.ifmo.se.pokemon.*;

/*
Mud-Slap inflicts damage and has a 99.6% chance of
lowering the target's accuracy stat by one stage.
 */

class MudSlap extends SpecialMove {
    protected MudSlap() {
        super(Type.GROUND, 20.0, 100.0);
    }
    @Override
    protected void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.996) {
            p.setMod(Stat.ACCURACY, -1);
        }
    }
    protected String describe() {
        return ("The target's Accuracy is lowering by one stage.");
    }
}
