/**
 * One of the businesses whose profit was
 * increased by advertising Dunno.
 */

public class Hostel extends Business {

    private int level;
    private int profit;
    private final int amountOfPeople = (int) (Math.random()*1000000);

    // Constructor for Hostel
    public Hostel(int level) {
        super(level);
        this.level = level;
    }

    //
    public void profit() {
        this.profit = level * amountOfPeople;
        System.out.println("Реклама Незнайки позволила заработать: ");
        System.out.println(profit);
    }

    public void story() {
        System.out.println("Незнайка живет в гостинице 'Изумруд', а это делает для бизнеса очень" +
                " хорошую рекламу. Подсчет прибыли за день доказал эту мысль.");
    }
}