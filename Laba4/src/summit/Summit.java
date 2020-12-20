package summit;

/*
public class Summit {

    Traveler traveler = new Traveler();
    Journalist journalist = new Journalist();
    Scientist scientist = new Scientist();
    Info info = new Info();
    Food food = new Food();
    Drink drink = new Drink();
    Seed seed = new Seed();
    Flight flight = new Flight();

    traveler.setName("Незнайка");
    traveler.setJob("Путешественник");
    traveler.setHomePlanet("Земля");

    journalist.setName("Звездочка");
    journalist.setJob("Журналист");
    journalist.setHomePlanet("Луна");

    scientist.setName("Аркадий");
    scientist.setJob("Ученый");
    scientist.setHomePlanet("Луна");

    traveler.takeSeed(); // To give the seeds

    flight.setStartPosition(Location.EARTH);
    flight.setFinishPosition(Location.MOON);
    flight.start();

    if (flight.where() == Location.SPACE) {
        while (traveler.getHealth()) {
            traveler.getHealthStatus();
            traveler.eat(food);
            traveler.getHealthStatus();
            traveler.drink(drink);
        }
    }

    flight.changeLocation(Location.MOON);

    if (flight.where() == Location.MOON) {
        journalist.setTheme("О планете");
        journalist.setOpponents(traveler.getName());
        journalist.say(traveler);
        journalist.setSeed(true);
        System.out.println("Теперь полученную информацию и эксклюзивные" + " " +
                "семена можно передать ученым.");
        System.out.println("Ученым нужно также передать информацию о путешественнике.");
        System.out.println(journalist.toString(traveler)); // <------------------------- toString here
        String data = info.getInfoAboutTheEarth();
        scientist.setData(data);
        scientist.explorePlanet();
        scientist.setSeed(true);
        scientist.explorePlant(seed);
        scientist.plant(seed);
        scientist.water(seed);
        seed.isPlant();
        boolean result = seed.equals("ATTGCCGACTAGATAT");
        if (!result) {
            System.out.println("Удивительно! У лунных и земных подсолнухов разные ДНК!");
        }
        else {
            System.out.println("Удивительно! У лунных и земных подсолнухов одинаковые ДНК!");
        }
        int hashOfMoonDna = scientist.hashCode("ATTGCCGACTAGATAT");
        int hashOfEarthDna = scientist.hashCode(seed.dna());
        if (hashOfEarthDna == hashOfMoonDna) {
            System.out.println("Полимеразная цепная реакция говорит, что ДНК одинаковые!");
        }
        else {
            System.out.println("Полимеразная цепная реакция также говорит, что ДНК разные!");
        }
    }
}
*/