package pokemons;

import attacks.AcidSpray;
import attacks.Pound;
import attacks.SludgeWave;
import ru.ifmo.se.pokemon.*;

/**
 * Class Gulpin is kind of pokemon.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Gulpin extends Pokemon {

    /** Constructor of this class */
    public Gulpin (String name, int level) {
            super(name, level);
            setStats(70, 90, 80, 50, 105, 96);
            setType(Type.POISON);
            setMove(new Pound(), new SludgeWave(), new AcidSpray());
    }
}
