public interface ForScientist {
    void explorePlanet();
    void explorePlant(Plant p);
    void plant(Plant p);
    void water(Plant p);
    void setJob(String job);
    String getJob();
    void setName(String name);
    String getName();
    boolean equals(String dna);
    int hashCode(String dna);
}
