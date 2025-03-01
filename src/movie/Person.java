package movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
@XmlType(propOrder = {"name", "birthday", "height", "weight"})
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDate birthday; //Поле может быть null
    private int height; //Значение поля должно быть больше 0
    private int weight; //Значение поля должно быть больше 0

    public Person() {

    }

    public Person(String name, int weight, int height) {
        setName(name);
        setWeight(weight);
        setHeight(height);
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public LocalDate getBirthday() {
        return birthday;
    }

    @XmlElement
    public int getHeight() {
        return height;
    }

    @XmlElement
    public int getWeight() {
        return weight;
    }

    public void setName(String name) {
        Objects.requireNonNull(name, "Строка не должна быть null");
        if(name.isEmpty()) {
            return;
        }
        this.name = name;
    }

    public void setWeight(int weight) {
        if(weight < 0) {
            return;
        }
        this.weight = weight;
    }

    public void setHeight(int height) {
        if(height < 0) {
            return;
        }
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Person person = (Person) o;
        return name.equals(person.name) &&
                height == person.height &&
                weight == person.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, weight);
    }
}
