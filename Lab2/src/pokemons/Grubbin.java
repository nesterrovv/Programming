package pokemons;

import attacks.Bite;
import attacks.MudSlap;
import ru.ifmo.se.pokemon.*;

/**
 * Class Grubbin is kind of pokemon.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Grubbin extends Pokemon {

    /** Constructor of this class */
    public Grubbin (String name, int level) {
        super(name, level);
        setStats(47, 62, 45, 55,45, 46);
        setType(Type.BUG);
        setMove(new Bite(), new MudSlap());
    }
}