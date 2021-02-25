public class House {

    Human human = new Human();

    public static class Office {
        private String aim = "ООО Общество гиганстких растений";
        private int actions = 5000000;
        private int stock = 0;
        private boolean hasChest = true;

        public void sell(Human human, int howMuch) {
            human.money(howMuch);
            actions -= howMuch;
            stock += 100*howMuch;
            System.out.println("Сделка завершена. Акции проданы.");
            System.out.println("Продано " + howMuch + " акций. Прибыль: " + actions);
        }


    }
}

