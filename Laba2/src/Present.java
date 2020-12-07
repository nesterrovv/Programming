import ru.ifmo.se.pokemon.*;

class Present extends PhysicalMove {
    protected Present () {
        super(Type.NORMAL, 1.0, 90.0);
    }
    @Override
    /*
    This attack randomly does one of the following:
    (20% chance) - Heals the target by 1/4 of its total HP.
    (40% chance) - This attack's base power is 40.
    (30% chance) - This attack's base power is 80.
    (10% chance) - This attack's base power is 120.
     */
    protected void applyOppDamage(Pokemon def, double damage) {
        double chance = Math.random() * 100;
        if (chance <= 40) {
            damage  = 40.0;
        }
        if (chance > 40 && chance <= 70) {
            damage  = 80.0;
        }
        if (chance > 40 && chance <= 90) {
            damage  = 120.0;
        }
        if (chance > 90) {
            damage = def.getHP() * 1.25;
        }
        def.setMod(Stat.HP, (int) Math.round(damage));
    }
    protected String describe() {
        return ("Random effect");
    }
}



