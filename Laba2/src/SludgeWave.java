import ru.ifmo.se.pokemon.*;
/*
Sludge Wave deals damage and has a 10% chance of poisoning the target.
 */
class SludgeWave extends SpecialMove {
    protected SludgeWave() {
        super(Type.POISON, 95.0, 100.0);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) {
            Effect.poison(p);
        }
    }
    protected String describe() {
        return ("Poisoned");
    }
}