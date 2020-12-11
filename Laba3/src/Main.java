public class Main {

    public static void main(String[] args) {

        Traveler traveler = new Traveler();
        Journalist journalist = new Journalist();
        Scientist scientist = new Scientist();
        Info info = new Info();
        Food food = new Food();
        Drink drink = new Drink();
        Seed seed = new Seed();
        //Location location = new Location();
        Flight flight = new Flight();

        traveler.setName("Незнайка");
        traveler.setJob("Путешественник");
        traveler.setJob("Путешественник");

        traveler.setName("Незнайка");
        traveler.setJob("Путешественник");
        traveler.setJob("Путешественник");

        traveler.setName("Незнайка");
        traveler.setJob("Путешественник");
        traveler.setJob("Путешественник");

        traveler.takeSeed(); // To give the seeds

        flight.setStartPosition(Location.EARTH);
        flight.setFinishPosition(Location.MOON);
        flight.start();

        if (flight.where() == Location.SPACE) {
            while (traveler.getHealth() != true) {
                traveler.getHealthStatus();
                traveler.eat(food);
                traveler.getHealthStatus();
                traveler.drink(drink);
            }
        }

        flight.changeLocation(Location.MOON);

        if (flight.where() == Location.MOON) {
            //journalist.say
        }



    }
}
