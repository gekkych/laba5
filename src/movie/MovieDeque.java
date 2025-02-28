package movie;

import java.util.*;

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
        movies.add(new Movie(nextFreeID, name, x, y, genre, mpaaRating, oscarCount, directorName, weight, height));
        sortMovieDeque();
    }

    public void removeById(long id) {
        Iterator<Movie> iterator = movies.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (movie.getID() == id) {
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
        movieList.sort(Comparator.comparingLong(Movie::getID));
        movies.clear();
        movies.addAll(movieList);
    }

    public void resetId() {
        nextFreeID = 1;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public ArrayDeque<Movie> getCollection() {
        return movies;
    }

    public String toString() {
        return "Тип ArrayDeque" + "\n" +
                "Создано " + getCreationDate() + "\n" +
                "Количество элементов " + getCollection().size() + "\n";
    }
}
