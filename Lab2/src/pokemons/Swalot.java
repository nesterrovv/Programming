package pokemons;

import attacks.AcidSpray;
import attacks.BodySlam;
import attacks.Pound;
import attacks.SludgeWave;
import ru.ifmo.se.pokemon.*;

/**
 * Class Swalot is kind of pokemon.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Swalot extends Gulpin {

    /** Constructor of this class */
    public Swalot (String name, int level) {
        super(name, level);
        setStats(100, 73, 83, 73, 83, 55);
        setType(Type.POISON);
        setMove(new Pound(), new SludgeWave(), new AcidSpray(), new BodySlam());
    }
}
