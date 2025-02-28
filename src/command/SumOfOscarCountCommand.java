package command;

import movie.Movie;
import movie.MovieDeque;

public class SumOfOscarCountCommand extends Command{
    private final MovieDeque movies;
    public SumOfOscarCountCommand(MovieDeque movies) {
        super("sum_of_oscar_count", false, false);
        this.movies = movies;
    }

    @Override
    public void start(String argument) {
        int oscarSum = 0;
        for (Movie movie : movies.getCollection()) {
            oscarSum += movie.getOscarsCount();
        }
        System.out.println("Сумма всех оскаров " + oscarSum);
    }

    @Override
    public String description() {
        return this.getName() + " - сумма всех оскаров";
    }
}
