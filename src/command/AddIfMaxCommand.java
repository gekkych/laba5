package command;

import movie.*;

import java.util.Scanner;

public class AddIfMaxCommand extends Command{
    private MovieDeque movies;
    private Scanner scanner;
    public AddIfMaxCommand(MovieDeque movies, Scanner scanner) {
        super("add_if_max", false, false);
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

        for(Movie movie : movies.getMovies()) {
            if (oscarCount <= movie.getOscarsCount()) {
                return;
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
