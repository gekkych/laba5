package command;

import movie.MovieDeque;

public class ClearCommand extends Command {
    private final MovieDeque movies;

    public ClearCommand(MovieDeque movies) {
        super("clear", true, false);
        this.movies = movies;
    }

    @Override
    public void start(String argument) {
        movies.getMovies().clear();
        movies.resetId();
        System.out.println("Коллекция очищена");
    }

    @Override
    public String description() {
        return getName() + " - удаляет все элементы коллекции";
    }

}
