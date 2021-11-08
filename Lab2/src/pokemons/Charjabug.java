package pokemons;

import attacks.Bite;
import attacks.Crunch;
import attacks.MudSlap;
import ru.ifmo.se.pokemon.*;

/**
 * Class Charjabug is kind of pokemon.
 * It is successor of Grubbin pokemon.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Charjabug extends Grubbin {

    /** Constructor of this class */
    public Charjabug (String name, int level) {
        super(name, level);
        setStats(47, 62, 45,55, 45, 46);
        setType(Type.BUG);
        setMove(new Bite(), new MudSlap(), new Crunch());
    }
}