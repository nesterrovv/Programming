package data;

import javax.xml.bind.annotation.*;


@XmlType(name = "coordinates")
@XmlRootElement
public class Coordinates {
    @XmlElement
    private long x; //Максимальное значение поля: 690
    @XmlElement
    private Float y; //Поле не может быть null

    public Coordinates(long x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {}

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
