package stonks;

public class Hostel extends Business{
    private final int visitors = (int) (Math.random()*100); // Randomly amount of visitors

    public void sell() {
        System.out.println("Реклама Незнайки позволяет гостинице" +
                " получать гораздо больше денег. Все хотят купить жить только в этой.");
        System.out.println("Сегодня в гостиницу заселились" + visitors + "посетителей");
    }
}
