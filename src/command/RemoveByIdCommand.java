package command;

import movie.MovieDeque;

public class RemoveByIdCommand extends Command{
    private final MovieDeque movies;
    public RemoveByIdCommand(MovieDeque movies) {
        super("remove_by_id", true, false);
        this.movies = movies;
    }

    @Override
    public void start(String argument) {
        long id = Long.parseLong(argument);
        movies.removeById(id);
    }

    @Override
    public String description() {
        return this.getName() + " - удалить элемент по ID";
    }
}
