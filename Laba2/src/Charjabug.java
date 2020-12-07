import ru.ifmo.se.pokemon.*;

public class Charjabug extends Grubbin {
    public Charjabug (String name, int level) {
        super(name, level);
        setStats(47, 62, 45,55, 45, 46);
        setType(Type.BUG);
        setMove(new Bite(), new MudSlap(), new Crunch());

    }
}