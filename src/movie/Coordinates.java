package movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"x", "y"})
public class Coordinates {
    private int x;
    private Double y; //Максимальное значение поля: 102, Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(int x, Double y) {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }

    @XmlElement
    public int getX() {
        return x;
    }

    public void setY(Double y) {
        if(y > 102) {
            return;
        }
        this.y = y;
    }

    @XmlElement
    public Double getY() {
        return y;
    }
}
