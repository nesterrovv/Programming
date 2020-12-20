package stonks;

public class Show {
    private final int visitors = (int) (Math.random()*100000); // Randomly amount of watched

    public void sell() {
        System.out.println("В актерах есть Незнайка - весомый повод посмотреть кино." +
                " С проката выходит гораздо больше денег.. Все хотят увидеть Легенду..");
        System.out.println("Сегодня кино посмотрели" + visitors + "человек");
    }
}
