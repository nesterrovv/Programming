import ru.ifmo.se.pokemon.*;

/*
SuperBite inflicts damage and has a 90% chance of causing the target to flinch.
 */

class SuperBite extends PhysicalMove {
    protected SuperBite() {
        super(Type.DARK, 60.0, 100.0);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.9) {
            Effect.flinch(p);
        }
    }
    protected String describe() {
        return ("Flinched");
    }
}