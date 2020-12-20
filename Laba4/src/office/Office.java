package office;

public class Office {

    private final String location = "Улица Фертинга дом 2, 3 этаж";
    private final String aim = "Продажа акций общества `Гигантские растения`";
    private final String team = "Незнайка, Джулио, Мига, Козлик";
    private int totalAmount = 5000000;
    private int money = 0;

    public void sellActions(int amount) {
        this.totalAmount -= amount;
        this.money += 100*amount;
    }

    public static String firstPosition() {
        return "Козлик посыльный! Они нужны для расширения возможостей бизнеса.";
    }

    public static String secondPosition() {
        return "Козлик швейцар! Они нужны для повышения престижа конторы!.";
    }

    public String solution() {
        return "Вмешался козлик и ситуация разрешилась.";
    }
}

