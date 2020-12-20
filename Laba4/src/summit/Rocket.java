package summit;

public interface Rocket {
    Location where();
    void changeLocation(Location l);
    void setStartPosition(Location l);
    void setFinishPosition(Location l);
}
