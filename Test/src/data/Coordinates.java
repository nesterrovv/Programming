package data;

import javax.xml.bind.annotation.*;

/**
 * @author Ivan Nesterov
 * @version 1.0
 * Class for describing field Coordinates of element
 */
@XmlType(name = "coordinates")
@XmlRootElement
public class Coordinates {
    /** field x */
    @XmlElement
    private Long x; //Максимальное значение поля: 690
    /** field x */
    @XmlElement
    private Float y; //Поле не может быть null

    /**
     * Constructor for making Coordinates field
     * @param x - x-coordinate
     * @param y - y-coordinate
     */
    public Coordinates(Long x, Float y) {
        this.x = x;
        this.y = y;
    }

    /** Default constructor */
    public Coordinates() {}

    /** Method for printing this field into a string representation */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Long getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
}
