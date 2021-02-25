import ru.ifmo.se.pokemon.*;

/*
Bite inflicts damage and has a 10% chance of causing the target to flinch.
 */

class Bite extends PhysicalMove {
    protected Bite() {
        super(Type.DARK, 60.0, 100.0);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) {
            Effect.flinch(p);
        }
    }
    protected String describe() {
        return("Flinched");
    }
}