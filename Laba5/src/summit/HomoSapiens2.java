package summit;

public abstract class HomoSapiens2{

    protected String name;
    protected String homePlanet;
    protected String job;
    protected boolean hasSeed;
    public abstract void setName(String name);              // FOR ALL
    public abstract String getName();                       // FOR ALL
    public abstract boolean hasSeed();                      // FOR ALL
    public abstract void setJob(String job);
    public abstract String getJob();
    public abstract void setHomePlanet(String homePlanet);
    public abstract String getHomePlanet();
}