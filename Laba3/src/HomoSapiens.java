public abstract class HomoSapiens {
    private String name;
    private String homePlanet;
    private String job;

    public abstract String say(String name);
    public abstract String eat(Food f);                     // FOR TRAVELLER
        //System.out.println("Путешественник поел" + f);
        //return "Трапеза прошла успешно";
    public abstract String drink(Drink d);                  // FOR TRAVELLER
        //System.out.println("Путешественник поел" + d);
        //return "Питье прошло успешно";
    public abstract String health();                        // FOR TRAVELLER
        //Return sth from enum State;
    public abstract String explore(Info i);                 // FOR SCIENTIST
    public abstract String explorePlant(Plant p);           // FOR SCIENTIST
    public abstract void setName(String name);              // FOR ALL
    public abstract String getName();                       // FOR ALL
    public abstract boolean hasSeed();                      // FOR ALL
}