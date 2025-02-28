package command;

import movie.MovieDeque;


public class InfoCommand extends Command{
    MovieDeque movies;
    public InfoCommand(MovieDeque movies) {
        super("info", false, false);
        this.movies = movies;
    }

    @Override
    public void start(String arguments) {
        System.out.printf(movies.toString());
    }

    @Override
    public String description() {
        return this.getName() + " - информация о коллекции";
    }
}
