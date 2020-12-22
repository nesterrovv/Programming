/**
 * One of the businesses whose profit was
 * increased by advertising Dunno.
 */

public class Show extends Business {

    private int level;
    private int profit;
    private final int amountOfPeople = (int) (Math.random()*1000000);

    // Constructor for Show
    public Show(int level) {
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
       System.out.println("Кино с Незнакой делает для киностудии очень" +
               " хорошую рекламу. Деньги с проката за день доказывают эту мысль.");
    }
}