package command;

import movie.Movie;
import movie.MovieDeque;

import java.util.ArrayDeque;

public class ShowCommand extends Command {
    ArrayDeque<Movie> movies;
    public ShowCommand(MovieDeque movieDeque) {
        super("show", false, false);
        this.movies = movieDeque.getCollection();
    }

    @Override
    public void start(String argument) {
        if (movies.isEmpty()) {
            System.out.println("Пустая коллекция");
        }
        for(Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    @Override
    public String description() {
        return this.getName() + " - список элементов коллекции";
    }
}
