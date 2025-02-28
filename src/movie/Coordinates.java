package movie;

public class Coordinates {
    private int x;
    private Double y; //Максимальное значение поля: 102, Поле не может быть null

    public Coordinates(int x, Double y) {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(Double y) {
        if(y > 102) {
            return;
        }
        this.y = y;
    }

    public Double getY() {
        return y;
    }
}
