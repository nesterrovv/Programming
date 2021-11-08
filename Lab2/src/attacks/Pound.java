package attacks;

import ru.ifmo.se.pokemon.*;

/**
 * Class Pound is successor of PhysicalMove attack.
 * Pound deals damage with no additional effect.
 * @author Ivan Nesterov
 * @version 1.0
 * @since 05.08.2021
 */
public class Pound extends PhysicalMove {

    /** Constructor of this class */
    public Pound() {
        super(Type.NORMAL, 40.0, 100.0);
    }

}
