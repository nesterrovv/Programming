import ru.ifmo.se.pokemon.*;
/*
This attack has a 30% chance
 to make the target flinch.
 */
class ZingZap extends PhysicalMove {
    protected ZingZap() {
        super(Type.ELECTRIC, 80.0, 100.0);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3) Effect.flinch(p);
    }
    protected String describe() {
        return("Flinched!");
    }
}
