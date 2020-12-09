public interface ForScientist {
    String explorePlanet();
    String explorePlant(Plant p);
    void getInformationAboutThePlanet(String dataAboutThePlanet);
    void getInformationAboutThePlant(String dataAboutThePlant);
    void plant(Plant p);
    void water(Plant p);
    void setJob(String job);
    String getJob();
    void setName(String name);
    String getName();
    boolean hasSeed();
}
