package command;

import movie.*;

import java.util.Iterator;
import java.util.Scanner;

public class RemoveIfLowerCommand extends Command{
    private MovieDeque movies;
    private Scanner scanner;
    public RemoveIfLowerCommand(MovieDeque movies, Scanner scanner) {
        super("remove_if_lower", true, false);
        this.movies = movies;
        this.scanner = scanner;
    }

    @Override
    public void start(String argument) {
        String title = movies.inputTitle(scanner);
        int x = movies.inputX(scanner);
        Double y = movies.inputY(scanner);
        MovieGenre genre = movies.inputGenre(scanner);
        MpaaRating rating = movies.inputRating(scanner);
        int oscarCount = movies.inputOscarCount(scanner);
        Person director = movies.inputDirector(scanner);

        Iterator<Movie> iterator = movies.getMovies().iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (oscarCount >= movie.getOscarsCount()) {
                System.out.println("Удалён фильм " + movie.getTitle() + " с айди " + movie.getId());
                iterator.remove();
            }
        }

        if (director != null) {
            if (director.getBirthday() != null) {
                movies.add(title, x, y, genre, rating, oscarCount, director.getName(), director.getBirthday(), director.getHeight(), director.getWeight());
            } else {
                movies.add(title, x, y, genre, rating, oscarCount, director.getName(), director.getHeight(), director.getWeight());
            }
            System.out.println("Фильм успешно добавлен в коллекцию");
            return;
        }
        movies.add(title, x, y, genre, rating, oscarCount);
        System.out.println("Фильм успешно добавлен в коллекцию");
    }

    @Override
    public String description() {
        return this.getName() + " - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
