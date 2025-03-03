package command;

import movie.*;

import java.time.LocalDate;
import java.util.Scanner;


public class UpdateCommand extends Command {
    MovieDeque movies;
    Scanner scanner;
    static final String GECKO = "\uD83E\uDD8E";

    public UpdateCommand(MovieDeque movies, Scanner scanner) {
        super("update", false, false);
        this.movies = movies;
        this.scanner = scanner;
    }

    @Override
    public void start(String argument) {
        try {
            long id = Long.parseLong(argument);
            for (Movie movie : movies.getMovies()) {
                if (movie.getId() == id) {
                    String title = movies.inputTitle(scanner);
                    int x = movies.inputX(scanner);
                    Double y = movies.inputY(scanner);
                    MovieGenre genre = movies.inputGenre(scanner);
                    MpaaRating rating = movies.inputRating(scanner);
                    int oscarCount = movies.inputOscarCount(scanner);
                    Person director = movies.inputDirector(scanner);

                    movie.setTitle(title);
                    movie.setCoordinates(new Coordinates(x, y));
                    movie.setGenre(genre);
                    movie.setMpaaRating(rating);
                    movie.setOscarsCount(oscarCount);
                    if (director != null) {
                        movie.setDirector(new Person(director.getName(), director.getBirthday(), director.getHeight(), director.getWeight()));
                        System.out.println("Фильм успешно обновлён");
                    }
                }
            }
            System.out.println("ID не найден");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат аргумента");
        }
    }


    @Override
    public String description() {
        return this.getName() + " - обновить значения элемента по ID";
    }
}