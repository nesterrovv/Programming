package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "location")
@XmlRootElement
public class Location {
    @XmlElement
    private long x;
    @XmlElement
    private Double y; //Поле не может быть null
    @XmlElement
    private String name; //Поле не может быть null

    public Location(long x, Double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Location() {}

    @Override
    public String toString() {
        return "Location{" +
                "x= " + x +
                ", y= " + y +
                ", name=' " + name + '\'' +
                '}';
    }
}
