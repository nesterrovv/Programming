package pokemons;

import attacks.Bite;
import attacks.Crunch;
import attacks.MudSlap;
import attacks.SuperBite;
import ru.ifmo.se.pokemon.*;

/**
 * Class Vikavolt is kind of pokemon.
 * It is successor of Charjabug pokemon.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Vikavolt extends Charjabug {

    /** Constructor of this class */
    public Vikavolt(String name, int level) {
        super(name, level);
        setStats(77, 70, 90, 145, 75, 43);
        setType(Type.BUG, Type.ELECTRIC);
        setMove(new Bite(), new MudSlap(), new Crunch(), new SuperBite());
    }
}