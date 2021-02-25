import ru.ifmo.se.pokemon.*;

/*
Crunch deals damage and has a 20% chance of
lowering the target's Defense by one stage.
 */
class Crunch extends PhysicalMove {
    protected Crunch() {
        super(Type.DARK, 80.0, 100.0);
    }
    @Override
    protected void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.2) {
            p.setMod(Stat.DEFENSE, -1);
        }
    }
    protected String describe() {
        return ("The target's Defense is lowering by one stage.");
    }
}