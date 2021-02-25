import ru.ifmo.se.pokemon.*;

/*
Pin Missile hits 2-5 times per turn used.
The probability of each interval and total power after each hit:
Hits	Prob.	Power
2	    3⁄8	    50
3	    3⁄8	    75
4	    1⁄8	    100
5	    1⁄8	    125
 */

class PinMissile extends PhysicalMove {
    protected PinMissile() {
        super(Type.BUG, 25.0, 95.0);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage) {
        double chance = Math.random() * 100;
        int counter = 1;
        if (chance <= 37.5) {
            damage = 50;
            counter = 2;
        }
        if (chance > 37.5 && chance <= 75.0) {
            damage = 75.0;
            counter = 3;
        }
        if (chance > 75.0 && chance <= 87.5) {
            damage = 100.0;
            counter = 4;
        }
        if (chance > 87.5) {
            damage = 125.0;
            counter = 5;
        }
        while (counter != 0) {
            def.setMod(Stat.HP, (int) Math.round(damage));
            counter --;
        }
    }
    protected String describe() {
        return ("Random effect");
    }
}