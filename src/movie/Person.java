package movie;

import exception.MovieFieldException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
@XmlType(propOrder = {"name", "birthday", "height", "weight"})
public class Person {
    private final MovieValidator validator = new MovieValidator();
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDate birthday; //Поле может быть null
    private int height; //Значение поля должно быть больше 0
    private int weight; //Значение поля должно быть больше 0

    public Person() {

    }

    public Person(String name, LocalDate birthday, int height, int weight) {
        setName(name);
        setBirthday(birthday);
        setWeight(weight);
        setHeight(height);
    }

    public Person(String name, int height, int weight) {
        setName(name);
        setWeight(weight);
        setHeight(height);
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement(nillable = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
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
        if (!validator.validateDirectorName(name)) throw new MovieFieldException("Ошибка при создании/обновлении элемента коллекции");
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setWeight(int weight) {
        if (!validator.validateDirectorWeight(weight)) throw new MovieFieldException("Ошибка при создании/обновлении элемента коллекции");
        this.weight = weight;
    }

    public void setHeight(int height) {
        if (!validator.validateDirectorHeight(height)) throw new MovieFieldException("Ошибка при создании/обновлении элемента коллекции");
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
