package command;

import movie.Movie;
import movie.MovieDeque;
import movie.MovieGenre;
import movie.MpaaRating;

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
        long id = Long.parseLong(argument);
        for (Movie movie : movies.getCollection()) {
            if (movie.getID() == id) {
                System.out.println("Введите название фильма или skip если хотите оставить таким же:");
                String name = update();
                if (!name.isEmpty()) {
                    movie.setTitle(name);
                }

                System.out.println("Введите координату X или skip если хотите оставить таким же:");
                String x = update();
                if(!x.isEmpty()) {
                    movie.getCoordinates().setX(Integer.parseInt(x));
                }

                System.out.println("Введите координату Y или skip если хотите оставить таким же:");
                String y = update();
                if (!y.isEmpty()) {
                    movie.getCoordinates().setY(Double.parseDouble(y));
                }

                System.out.println("Введите жанр фильма или skip если хотите оставить таким же:");
                String genre = update();
                if(!genre.isEmpty()) {
                    movie.setGenre(switch (genre) {
                        case "action" -> MovieGenre.ACTION;
                        case "comedy" -> MovieGenre.COMEDY;
                        case "science fiction" -> MovieGenre.SCIENCE_FICTION;
                        default -> null;
                    });
                }

                System.out.println("Введите возрастной рейтинг фильма или skip если хотите оставить таким же:");
                String rating = update();
                if (!rating.isEmpty()) {
                    movie.setMpaaRating(switch (rating.toLowerCase()) {
                        case "g" -> MpaaRating.G;
                        case "pg" -> MpaaRating.PG;
                        case "pg 13" -> MpaaRating.PG_13;
                        case "nc 17" -> MpaaRating.NC_17;
                        default -> null;
                    });
                }

                System.out.println("Введите количество оскаров или skip если хотите оставить таким же");
                String oscarCount = update();
                if (!oscarCount.isEmpty()) {
                    movie.setOscarsCount(Integer.parseInt(oscarCount));
                }

                System.out.println("Введите имя директора или skip если хотите оставить таким же:");
                String directorName = update();
                if (!directorName.isEmpty()) {
                    movie.getDirector().setName(directorName);
                }

                System.out.println("Введите вес режиссёра или skip если хотите оставить таким же:");
                String directorWeight = update();
                if(!directorWeight.isEmpty()) {
                    movie.getDirector().setWeight(Integer.parseInt(directorWeight));
                }

                System.out.println("Введите рост режиссёра или skip если хотите оставить таким же:");
                String directorHeight = update();
                if(!directorHeight.isEmpty()) {
                    movie.getDirector().setHeight(Integer.parseInt((directorWeight)));
                }
            }
        }
    }

    private String update() {
        System.out.print(GECKO + " > " );
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("skip")) {
            return "";
        } else {
            return input;
        }
    }

    @Override
    public String description() {
        return this.getName() + " - обновить значения элемента по ID";
    }
}
