import ru.ifmo.se.pokemon.*;

public class Vikavolt extends Charjabug {
    public Vikavolt(String name, int level) {
        super(name, level);
        setStats(77, 70, 90, 145, 75, 43);
        setType(Type.BUG, Type.ELECTRIC);
        setMove(new Bite(), new MudSlap(), new Crunch(), new SuperBite());
    }
}