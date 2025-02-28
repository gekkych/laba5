package command;

import movie.MovieDeque;
import movie.MovieGenre;
import movie.MpaaRating;

import java.util.Scanner;

public class AddCommand extends Command{
    static final String GECKO = "\uD83E\uDD8E";
    private final MovieDeque movies;
    private final Scanner scanner;
    public AddCommand(MovieDeque movies, Scanner scanner) {
        super("add", false, false);
        this.movies = movies;
        this.scanner = scanner;
    }

    @Override
    public void start(String argument) {
        System.out.println("Введите название фильма:");
        System.out.print(GECKO + " > " );
        String name = scanner.nextLine().trim();

        System.out.println("Введите координату X:");
        System.out.print(GECKO + " > " );
        int x = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Введите координату Y:");
        System.out.print(GECKO + " > " );
        Double y = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Введите жанр фильма:");
        System.out.print(GECKO + " > " );
        MovieGenre movieGenre = switch (scanner.nextLine().trim().toLowerCase()) {
            case "action" -> MovieGenre.ACTION;
            case "comedy" -> MovieGenre.COMEDY;
            case "science fiction" -> MovieGenre.SCIENCE_FICTION;
            default -> null;
        };

        System.out.println("Введите возрастной рейтинг фильма");
        System.out.print(GECKO + " > " );
        MpaaRating mpaaRating = switch (scanner.nextLine().trim().toLowerCase()) {
            case "g" -> MpaaRating.G;
            case "pg" -> MpaaRating.PG;
            case "pg 13" -> MpaaRating.PG_13;
            case "nc 17" -> MpaaRating.NC_17;
            default -> null;
        };

        System.out.println("Введите количество оскаров");
        System.out.print(GECKO + " > " );
        int oscarCount = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Введите имя директора:");
        System.out.print(GECKO + " > " );
        String directorName = scanner.nextLine().trim();

        System.out.println("Введите вес режиссёра:");
        System.out.print(GECKO + " > " );
        int directorWeight = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Введите рост режиссёра:");
        System.out.print(GECKO + " > " );
        int directorHeight = Integer.parseInt(scanner.nextLine().trim());

        movies.add(name, x, y, movieGenre, mpaaRating, oscarCount, directorName, directorWeight, directorHeight);
    }

    @Override
    public String description() {
        return this.getName() + " - добавить новый элемент в коллекцию";
    }
}
