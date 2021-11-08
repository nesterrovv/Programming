package pokemons;

import attacks.PinMissile;
import attacks.Present;
import attacks.Twineedle;
import attacks.ZingZap;
import ru.ifmo.se.pokemon.*;

/**
 * Class Mimikyu is kind of pokemon.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Mimikyu extends Pokemon {

    /** Constructor of this class */
    public Mimikyu (String name, int level) {
        super(name, level);
        setStats(55, 90, 80, 50, 105, 96);
        setType(Type.GHOST, Type.FAIRY); // Types of Pokemon
        setMove(new Present(), new PinMissile(), new ZingZap(), new Twineedle());
    }
}

