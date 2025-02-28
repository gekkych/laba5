package movie;

import java.util.Objects;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDate birthday; //Поле может быть null
    private int height; //Значение поля должно быть больше 0
    private int weight; //Значение поля должно быть больше 0

    public Person(String name, int weight, int height) {
        setName(name);
        setWeight(weight);
        setHeight(height);
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
}
