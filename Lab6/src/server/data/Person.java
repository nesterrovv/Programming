package server.data;

import javax.xml.bind.annotation.*;
import java.time.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ivan Nesterov
 * @version 1.0
 * Class for describing a person - element of collection
 */
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.NONE)
public class Person {
    /** Field ID */
    @XmlElement
    private int id; // The field value must be greater than 0, The value of this field must be unique, The value of this field must be generated automatically
    /** Field name */
    @XmlElement
    private String name; // Field cannot be null, string cannot be empty
    /** Field coordinates */
    @XmlElement
    private Coordinates coordinates; // Field cannot be null
    /** Field creation date */
    @XmlElement
    private String creationDate; // The field cannot be null, the value of this field must be generated automatically
    /** Field height */
    @XmlElement
    private long height; // The field value must be greater than 0
    /** Field eye color */
    @XmlElement
    private EyeColor eyeColor; // Field cannot be null
    /** Field hair color */
    @XmlElement
    private HairColor hairColor; // Field cannot be null
    /** Field nationality */
    @XmlElement
    private Country nationality; // Field cannot be null
    /** Field location */
    @XmlElement
    private Location location; // Field cannot be null

    /** Constructor for making a person */
    public Person(int id, String name, Coordinates coordinates, String creationDate, long height, EyeColor eyeColor, HairColor hairColor, Country nationality, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = returnCreationDate();
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    /** Default constructor */
    public Person() {}

    /** Method for printing Person-class object into string representation */
    @Override
    public String toString() {
        return "Person{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", coordinates = " + coordinates +
                ", creationDate = " + creationDate +
                ", height = " + height +
                ", eyeColor = " + eyeColor +
                ", hairColor = " + hairColor +
                ", nationality = " + nationality +
                ", location = " + location +
                '}';
    }


    /**
     * Method for get ID
     * @return int id
     */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for get name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /** Method for passing a value to the name field */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for get coordinates
     * @return Coordinates coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /** Method for passing a value to the coordinates field */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Method for get height
     * @return long height
     */
    public long getHeight() {
        return height;
    }

    /** Method for passing a value to the height field */
    public void setHeight(long height) {
        this.height = height;
    }

    /**
     * Method for get eye color
     * @return EyeColor eyeColor
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /** Method for passing a value to the eyeColor field */
    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Method for get hair color
     * @return HairColor hairColor
     */
    public HairColor getHairColor() {
        return hairColor;
    }

    /** Method for passing a value to the hairColor field */
    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Method for get nationality
     * @return Country nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /** Method for passing a value to the nationality field */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * Method for get location
     * @return Location location
     */
    public Location getLocation() {
        return location;
    }

    /** Method for passing a value to the location field */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Method for get current date into string representation
     * @return String date
     */
    public String returnCreationDate() {
        return ZonedDateTime.now().toString();
    }
}