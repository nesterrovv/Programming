import ru.ifmo.se.pokemon.*;

/*
Twineedle deals damage and will strike twice
(with 25 base power each time).
It has a 20% chance of poisoning the target.
 */

class Twineedle extends PhysicalMove {
    protected Twineedle() {
        super(Type.BUG, 25.0, 100.0);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.2) Effect.poison(p);
        }
    protected void applyOppDamage(Pokemon p) {
        int counter = 2;
        while (counter != 0) {
            p.setMod(Stat.HP, 1);
            counter --;
        }
    }
    protected String describe() {
        return ("Poisoned");
    }
}