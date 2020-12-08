public abstract class HomoSapiens {
    private String name;
    private String homePlanet;
    private String job;

    public abstract String eat(Food f);
        //System.out.println("Путешественник поел" + f);
        //return "Трапеза прошла успешно";
    public abstract String drink(Drink d);
        //System.out.println("Путешественник поел" + d);
        //return "Питье прошло успешно";
    public abstract String health();
        //Return sth from enum State;
    public abstract String explore(Info i);
    public abstract String explorePlant(Plant p);
    //public abstract setName(String s);
    //public abstract getName();
    //public abstract boolean hasSeed();
}