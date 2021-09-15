package ru.job4j.serialization.jxab;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.StringJoiner;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int founded;

    @XmlAttribute
    private boolean isResortTown;

    private Coordinates coordinates;

    @XmlElementWrapper(name = "dictricts")
    @XmlElement(name = "district")
    String[] districts;

    public City() {

    }

    public City(String name, int founded, boolean isResortTown, Coordinates coordinates, String[] districts) {
        this.name = name;
        this.founded = founded;
        this.isResortTown = isResortTown;
        this.coordinates = coordinates;
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "City{"
                + "name='" + name + '\''
                + ", founded=" + founded
                + ", isResortTown=" + isResortTown
                + ", coordinates=" + coordinates
                + ", districts=" + Arrays.toString(districts)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final City city = new City("Пермь", 1723, false,
                new Coordinates(58.0105D, 56.2502),
                new String[] {"Ленинский", "Кировский", "Орджоникидзевский", "Дзержинский",
                        "Свердловский", "Индустриальный", "Мотовилихинский"}
                        );
        String xml = null;
        System.out.println(String.format("Объект до сериализации:%s%s",
                System.lineSeparator(), city.toString()));
        JAXBContext context = JAXBContext.newInstance(City.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(city, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            City result = (City) unmarshaller.unmarshal(reader);
            StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
            stringJoiner.add("Объект после десериализации:")
                    .add(result.toString())
                    .add("Объект, сериализованный в XML:")
                    .add(xml);
            System.out.println(stringJoiner.toString());
        }
    }
}
