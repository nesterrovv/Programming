package stonks;

public class Clinic extends Business {

    private final int visitors = (int) (Math.random()*100000); // Randomly amount of visitors

    public void sell() {
        System.out.println("Реклама Незнайки позволяет доктору Шприцу" +
                " получать гораздо больше денег. Все хотят лечиться только у него.");
        System.out.println("Сегодня к нему пришли" + visitors + "посетителей");
    }

}
