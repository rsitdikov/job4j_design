package ru.job4j.serialization.jxab;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinates")
public class Coordinates {
    @XmlAttribute
    private double lat;

    @XmlAttribute
    private double lon;

    public Coordinates() {

    }

    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Coordinates{"
                + "lat=" + lat
                + ", lon=" + lon
                + '}';
    }
}
