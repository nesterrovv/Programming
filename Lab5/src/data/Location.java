package data;

public class Location {
    private long x;
    private Double y; //Поле не может быть null
    private String name; //Поле не может быть null

    public Location(long x, Double y, String name) {
        this.x = (long) x;
        this.y = new Double(y);
        this.name = new String(name);
    }

}
