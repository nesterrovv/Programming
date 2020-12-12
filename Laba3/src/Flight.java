public class Flight implements Rocket {

    private Location startPosition;
    private Location finishPosition;
    private Location currentPosition;

    public Location where() {
        return this.currentPosition;
    }

    public void changeLocation(Location l) {
        this.currentPosition = l;
    }

    public void setStartPosition(Location l) { startPosition = l;
    }

    public void setFinishPosition(Location l) {
        this.finishPosition = l;
    }

    public void start() {
        this.currentPosition = Location.SPACE;
    }
}
