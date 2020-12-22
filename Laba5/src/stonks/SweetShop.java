package stonks;

public class SweetShop extends Business {

    private final int visitors = (int) (Math.random()*100000); // Randomly amount of visitors

    public void sell() {
        System.out.println("Реклама Незнайки позволяет магазину" +
                " получать гораздо больше денег. Все хотят купить у них как можно больше сладостей.");
        System.out.println("Сегодня в магазин пришли" + visitors + "посетителей");
    }

}
