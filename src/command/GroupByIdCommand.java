package command;

import movie.Movie;
import movie.MovieDeque;

import java.util.ArrayList;

public class GroupByIdCommand extends Command{
    MovieDeque movies;

    public GroupByIdCommand(MovieDeque movies) {
        super("group_by_id", false, false);
        this.movies = movies;
    }

    @Override
    public void start(String argument) {
        ArrayList<String> actionFilms = new ArrayList<>();
        ArrayList<String> comedyFilms = new ArrayList<>();
        ArrayList<String> scifiFilms = new ArrayList<>();
        for (Movie movie : movies.getMovies()) {
            if (movie.getGenre() == null) continue;
            switch (movie.getGenre()) {
                case ACTION -> actionFilms.add(movie.toString());
                case COMEDY -> comedyFilms.add(movie.toString());
                case SCIENCE_FICTION -> scifiFilms.add(movie.toString());
            }
        }
        if (!actionFilms.isEmpty()) {
            System.out.println("Боевики:");
            for (String s : actionFilms) {
                System.out.println(s);
            }
        }
        if (!comedyFilms.isEmpty()) {
            System.out.println("Комедии: ");
            for (String s : comedyFilms) {
                System.out.println(s);
            }
        }
        if (!scifiFilms.isEmpty()) {
            System.out.println("Научная фантастика: ");
            for (String s : scifiFilms) {
                System.out.println(s);
            }
        }
    }

    @Override
    public String description() {
        return getName() + " - сгруппировать фильма по ID";
    }
}
