package command;

import movie.Movie;
import movie.MovieDeque;

public class AverageOfOscarCountCommand extends Command {
    private final MovieDeque movies;
    public AverageOfOscarCountCommand(MovieDeque movies) {
        super("average_of_oscar_count", false, false);
        this.movies = movies;
    }

    @Override
    public void start(String argument) {
        int oscarSum = 0;
        for (Movie movie : movies.getCollection()) {
            oscarSum += movie.getOscarsCount();
        }
        System.out.println("Среднее количество оскаров " + (oscarSum/movies.getCollection().size()));
    }

    @Override
    public String description() {
        return this.getName() + " - среднее количество оскаров";
    }
}
