package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Ivan Nesterov
 * @version 1.0
 * Class for describing a field Location of element
 */
@XmlType(name = "location")
@XmlRootElement
public class Location {
    /** field x */
    @XmlElement
    private long x;
    /** field y */
    @XmlElement
    private Double y; //Поле не может быть null
    /** field name */
    @XmlElement
    private String name; //Поле не может быть null

    /**
     * Constructor
     * @param x - x-coordinate
     * @param y - y-coordinate
     * @param name - name of location
     */
    public Location(long x, Double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * Default constructor
     */
    public Location() {}

    /** Method for printing this field into a string representation */
    @Override
    public String toString() {
        return "Location{" +
                "x= " + x +
                ", y= " + y +
                ", name=' " + name + '\'' +
                '}';
    }
}
