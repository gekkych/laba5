package movie;

import exception.MovieFieldException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
@XmlType(propOrder = {"id", "title", "genre", "mpaaRating", "director", "oscarsCount", "coordinates", "creationDate"})
public class Movie implements Comparable<Movie> {
    private final MovieValidator validator = new MovieValidator();
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String title; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person director; //Поле может быть null

    public Movie() {
    }

    protected Movie(long id, String title, int x, Double y, MovieGenre genre, MpaaRating mpaaRating, int oscarsCount, String directorName, LocalDate birthday, int weight, int height) {
        this.id = id;
        setTitle(title);
        setCoordinates(new Coordinates(x, y));
        this.creationDate = new Date();
        setGenre(genre);
        setMpaaRating(mpaaRating);
        this.oscarsCount = oscarsCount;
        setDirector(new Person(directorName, birthday, weight, height));
    }

    protected Movie(long id, String title, int x, Double y, MovieGenre genre, MpaaRating mpaaRating, int oscarsCount) {
        this.id = id;
        setTitle(title);
        setCoordinates(new Coordinates(x, y));
        this.creationDate = new Date();
        setGenre(genre);
        setMpaaRating(mpaaRating);
        this.oscarsCount = oscarsCount;
    }

    protected Movie(long id, String title, int x, Double y, MovieGenre genre, MpaaRating mpaaRating, int oscarsCount, String directorName, int weight, int height) {
        this.id = id;
        setTitle(title);
        setCoordinates(new Coordinates(x, y));
        this.creationDate = new Date();
        setGenre(genre);
        setMpaaRating(mpaaRating);
        this.oscarsCount = oscarsCount;
        setDirector(new Person(directorName, height, weight));
    }

    @Override
    public int compareTo(Movie other) {
        return Integer.compare(this.getOscarsCount(), other.getOscarsCount());
    }

    public void setTitle(String title) {
        if (!validator.validateTitle(title))
            throw new MovieFieldException("Ошибка при создании/обновлении элемента коллекции");
        this.title = title;
    }

    @XmlElement
    public Date getCreationDate() {
        return creationDate;
    }

    @XmlElement
    public String getTitle() {
        return this.title;
    }

    @XmlElement
    public MovieGenre getGenre() {
        return genre;
    }

    @XmlElement(nillable = true)
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setOscarsCount(int count) {
        if (!validator.validateOscarCount(count))
            throw new MovieFieldException("Ошибка при создании/обновлении элемента коллекции");
        this.oscarsCount = count;
    }

    public void setGenre(MovieGenre genre) { // реализовать проверку на null
        if (!validator.validateGenre(genre))
            throw new MovieFieldException("Ошибка при создании/обновлении элемента коллекции");
        this.genre = genre;
    }

    public void setMpaaRating(MpaaRating rating) { // реализовать проверку на null
        this.mpaaRating = rating;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    @XmlElement(nillable = true)
    public Person getDirector() {
        return director;
    }

    @XmlElement
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement
    public int getOscarsCount() {
        return oscarsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return oscarsCount == movie.oscarsCount && title.equals(movie.title) && genre.equals(movie.genre) && director.equals(movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oscarsCount, title, genre, director);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(id).append(")").append(" ").append(title).append("   Жанр ").append(getGenre()).append("   Количество Оскаров ").append(oscarsCount);
        if (mpaaRating != null) {
            builder.append("    Возрастное ограничение ").append(mpaaRating);
        }
        if (director != null) {
            builder.append("    Режиссёр ").append(director.getName());
            if (director.getBirthday() != null) {
                builder.append(" ").append(director.getBirthday());
            }
        }

        return builder.toString();
    }

}
