import ru.ifmo.se.pokemon.*;

public class Mimikyu extends Pokemon {
    public Mimikyu (String name, int level) {
        super(name, level); // Parametres of superclass
        setStats(55, 90, 80, 50, 105, 96); // Basic characteristics
        setType(Type.GHOST, Type.FAIRY); // Types of Pokemon
        setMove(new Present(), new PinMissile(), new ZingZap(), new Twineedle()); // Attacks
    }
}

