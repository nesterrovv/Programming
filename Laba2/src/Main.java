import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Mimikyu("Crosh", 2);
        Pokemon p2 = new Gulpin("Yozjik", 3);
        Pokemon p3 = new Swalot("Nusha", 1);
        Pokemon p4 = new Grubbin("Pin", 1);
        Pokemon p5 = new Charjabug("Copatich", 3);
        Pokemon p6 = new Vikavolt("Sovunija", 2);
        b.addFoe(p1);
        b.addFoe(p2);
        b.addFoe(p3);
        b.addAlly(p4);
        b.addAlly(p5);
        b.addAlly(p6);
        b.go();
    }
}