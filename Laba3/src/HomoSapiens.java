public abstract class HomoSapiens {
    private String name;
    private String homePlanet;
    private String job;

    public abstract String eat(Food f);
    public abstract String eat(Drink d);
    public abstract String health();
    public abstract String explore(Info i);
    public abstract String explorePlant(Plant p);
    public abstract boolean hasSeed();
}