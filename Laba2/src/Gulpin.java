import ru.ifmo.se.pokemon.*;

public class Gulpin extends Pokemon {
    public Gulpin (String name, int level) {
            super(name, level);
            setStats(70, 90, 80, 50, 105, 96);
            setType(Type.POISON);
            setMove(new Pound(), new SludgeWave(), new AcidSpray());
    }
}
