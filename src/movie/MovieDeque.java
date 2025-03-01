package movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class MovieDeque {
    private final ArrayDeque<Movie> movies = new ArrayDeque<>();
    private final Set<Long> idSet = new HashSet<>();
    private long nextFreeID = 1;
    private final Date creationDate;

    public MovieDeque() {
        creationDate = new Date();
    }

    public void add(String name, int x, Double y, MovieGenre genre, MpaaRating mpaaRating, int oscarCount, String directorName, int weight, int height) {
        while (idSet.contains(nextFreeID)) {
            nextFreeID++;
        }
        idSet.add(nextFreeID);
        Movie newMovie = new Movie(nextFreeID, name, x, y, genre, mpaaRating, oscarCount, directorName, weight, height);
        for(Movie movie : movies) {
            if(movie.equals(newMovie)) {
                System.out.println("Фильм уже есть в коллекции");
                return;
            }
        }
        movies.add(newMovie);
        sortMovieDeque();
    }

    public void removeById(long id) {
        Iterator<Movie> iterator = movies.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (movie.getId() == id) {
                iterator.remove();
                idSet.remove(id);
                nextFreeID = Math.min(nextFreeID, id);
                return;
            }
        }
        System.out.println("Фильм с ID " + id + " не найден");
    }

    public void sortMovieDeque() {
        ArrayList<Movie> movieList = new ArrayList<>(movies);
        movieList.sort(Comparator.comparingLong(Movie::getId));
        movies.clear();
        movies.addAll(movieList);
    }

    public void resetId() {
        nextFreeID = 1;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @XmlElement
    public ArrayDeque<Movie> getMovies() {
        return movies;
    }

    public String toString() {
        return "Тип ArrayDeque" + "\n" +
                "Создано " + getCreationDate() + "\n" +
                "Количество элементов " + getMovies().size() + "\n";
    }
}
