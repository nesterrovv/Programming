package interview;

public class Traveler extends HomoSapiens{

    public void takeInfo() {
        System.out.println("Путешественник рассказывает о родной планете.");
    }

    public void angry() {
        System.out.println(getName() + "начал сердиться");
        System.out.println(getName() + "отвесил крепкого пинка надоедливому журналисту");
    }

    public void advertise() {
        System.out.println("Из-за висящего на шее плаката, путешественник невольно " +
                           "что-то рекламирует...");
    }

}
