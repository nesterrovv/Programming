package data;

public class Coordinates {
    private long x; //Максимальное значение поля: 690
    private Float y; //Поле не может быть null

    public Coordinates(long x, Float y) {
        this.x = (long) x;
        this.y = new Float(y);
    }

}
