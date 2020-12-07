import ru.ifmo.se.pokemon.*;

/*
Body Slam deals damage and has a 30% chance of paralyzing the target.
 */

class BodySlam extends PhysicalMove {
    protected BodySlam() {
        super(Type.NORMAL, 85.0, 100.0);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3) {
            Effect.paralyze(p);
        }
    }
    protected String describe() {
        return("Paralized");
    }
}
