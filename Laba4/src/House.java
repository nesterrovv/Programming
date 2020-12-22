public class House {

    Human human = new Human();

    public static class Office {
        private String aim = "ООО Общество гиганстких растений";
        private boolean hasChest = true;

        public void sell(Human human) {
            human.money(10);
            System.out.println("Сделка завершена. Акции проданы.");
        }


    }
}

