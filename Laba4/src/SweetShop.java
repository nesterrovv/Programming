/**
 * One of the businesses whose profit was
 * increased by advertising Dunno
 */


public class SweetShop extends Business {

    private int level;
    private int profit;
    private final int amountOfPeople = (int) (Math.random()*1000000);

    // Constructor for Sweet Shop
    public SweetShop(int level) {
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
        System.out.println("Незнайка на встрече с людьми держал плакат с призывом " +
                "покупать сладости фабрики 'Заря', а это делает для бизнеса очень" +
                " хорошую рекламу. Подсчет прибыли за день доказал эту мысль.");
    }
}
