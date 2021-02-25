import ru.ifmo.se.pokemon.*;

public class Swalot extends Pokemon {
    public Swalot (String name, int level) {
        super(name, level);
        setStats(100, 73, 83, 73, 83, 55);
        setType(Type.POISON);
        setMove(new Pound(), new SludgeWave(), new AcidSpray(), new BodySlam());
    }
}
