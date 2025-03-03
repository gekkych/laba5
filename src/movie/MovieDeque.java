package movie;

import exception.IdException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@XmlRootElement
public class MovieDeque {
    private final MovieValidator validator = new MovieValidator();
    private final ArrayDeque<Movie> movies = new ArrayDeque<>();
    private final Set<Long> idSet = new HashSet<>();
    private long nextFreeID = 1;
    private final Date creationDate;
    static final String GECKO = "\uD83E\uDD8E";


    public MovieDeque() {
        creationDate = new Date();
    }

    public Set<Long> getIdSet() {
        return idSet;
    }

    public void manageDeque() {
        int counter = 0;
        for (Movie movie : movies) {
            idSet.add(movie.getId());
            counter++;

            if (idSet.size() != counter) {
                throw new IdException();
            }

        }
    }

    private long generateID() {
        while (idSet.contains(nextFreeID)) {
            nextFreeID++;
        }
        idSet.add(nextFreeID);
        return nextFreeID;
    }

    public void add(String title, int x, Double y, MovieGenre genre, MpaaRating mpaaRating, int oscarCount, String directorName, LocalDate birthday, int weight, int height) {
        Movie newMovie = new Movie(generateID(), title, x, y, genre, mpaaRating, oscarCount, directorName, birthday, weight, height);
        movies.add(newMovie);
        sortMovieDeque();
    }

    public void add(String title, int x, Double y, MovieGenre genre, MpaaRating mpaaRating, int oscarCount) {
        Movie newMovie = new Movie(generateID(), title, x, y, genre, mpaaRating, oscarCount);
        movies.add(newMovie);
        sortMovieDeque();
    }

    public void add(String title, int x, Double y, MovieGenre genre, MpaaRating mpaaRating, int oscarCount, String directorName, int weight, int height) {
        Movie newMovie = new Movie(generateID(), title, x, y, genre, mpaaRating, oscarCount, directorName, weight, height);
        movies.add(newMovie);
        sortMovieDeque();
    }

    public String inputTitle(Scanner scanner) {
        while (true) {
            System.out.println("Введите название фильма:");
            System.out.print(GECKO + " > ");
            String title = scanner.nextLine().trim();
            if (validator.validateTitle(title)) return title;
            System.out.println("Неверный ввод, попробуйте ещё раз");
        }
    }

    public int inputX(Scanner scanner) {
        while (true) {
            System.out.println("Введите координату Х:");
            System.out.print(GECKO + " > ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
            }
        }
    }

    public Double inputY(Scanner scanner) {
        while (true) {
            System.out.println("Введите координату Y:");
            System.out.print(GECKO + " > ");
            try {
                Double y = Double.parseDouble(scanner.nextLine().trim());
                if (validator.validateY(y)) return y;
                System.out.println("Неверный ввод, попробуйте ещё раз");
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
            }
        }
    }

    public int inputOscarCount(Scanner scanner) {
        while (true) {
            System.out.println("Введите количество оскаров:");
            System.out.print(GECKO + " > ");
            try {
                int oscarCount = Integer.parseInt(scanner.nextLine().trim());
                if (validator.validateOscarCount(oscarCount)) return oscarCount;
                System.out.println("Неверный ввод, попробуйте ещё раз");
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
            }
        }
    }

    public MovieGenre inputGenre(Scanner scanner) {
        while (true) {
            System.out.println("Введите жанр фильма:");
            System.out.println("Action, Comedy, Science fiction");
            System.out.print(GECKO + " > ");
            MovieGenre genre = switch (scanner.nextLine().trim().toLowerCase()) {
                case "action" -> MovieGenre.ACTION;
                case "comedy" -> MovieGenre.COMEDY;
                case "science fiction" -> MovieGenre.SCIENCE_FICTION;
                default -> null;
            };
            if (validator.validateGenre(genre)) return genre;
            System.out.println("Неверный ввод, попробуйте ещё раз");
        }
    }

    public MpaaRating inputRating(Scanner scanner) {
        while (true) {
            System.out.println("Введите возрастной рейтинг фильма (нажмите Enter если хотите оставить поле пустым):");
            System.out.println("G, PG, PG 13, NC 17");
            System.out.print(GECKO + " > ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                return null;
            }
            MpaaRating mpaaRating = switch (input) {
                case "g" -> MpaaRating.G;
                case "pg" -> MpaaRating.PG;
                case "pg 13" -> MpaaRating.PG_13;
                case "nc 17" -> MpaaRating.NC_17;
                default -> null;
            };
            if (mpaaRating == null) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
                continue;
            }
            return mpaaRating;
        }
    }

    public Person inputDirector(Scanner scanner) {
        final String[] CONFIRMATION_WORDS = {"y", "yes"};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String directorName;
        LocalDate birthday;
        int height;
        int weight;
        System.out.println("Вы хотите ввести информацию о режиссёре? [y/n]:");
        System.out.print(GECKO + " > ");
        String input = scanner.nextLine().trim();
        for (String word : CONFIRMATION_WORDS) {
            if (!input.equalsIgnoreCase(word)) {
                return null;
            }
        }

        while (true) {
            System.out.println("Введите имя режиссёра:");
            System.out.print(GECKO + " > ");
            directorName = scanner.nextLine().trim();
            if (validator.validateDirectorName(directorName)) break;
            System.out.println("Неверный ввод, попробуйте ещё раз");
        }

        while (true) {
            System.out.println("Введите дату рождения режиссёра в формате \"dd-MM-yyyy:\" (нажмите Enter если хотите оставить поле пустым):");
            System.out.print(GECKO + " > ");
            String birthdayInput = scanner.nextLine().trim();
            if (birthdayInput.isEmpty()) {
                return null;
            }
            try {
                birthday = LocalDate.parse(birthdayInput, formatter);
                if (validator.validateDirectorBirthday(birthday)) break;
                System.out.println("Неверный ввод, попробуйте ещё раз");
            } catch (DateTimeParseException e) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
            }
        }

        while (true) {
            System.out.println("Введите рост режиссёра:");
            System.out.print(GECKO + " > ");
            try {
                height = Integer.parseInt(scanner.nextLine().trim());
                if (validator.validateDirectorHeight(height)) break;
                System.out.println("Неверный ввод, попробуйте ещё раз");
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
            }
        }

        while (true) {
            System.out.println("Введите вес режиссёра:");
            System.out.print(GECKO + " > ");
            try {
                weight = Integer.parseInt(scanner.nextLine().trim());
                if (validator.validateDirectorWeight(weight)) break;
                System.out.println("Неверный ввод, попробуйте ещё раз");
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
            }
        }
        return new Person(directorName, birthday, height, weight);
    }

    public void removeById(long id) {
        Iterator<Movie> iterator = movies.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (movie.getId() == id) {
                iterator.remove();
                idSet.remove(id);
                nextFreeID = Math.min(nextFreeID, id);
                System.out.println("Фильм успешно удалён из коллекции");
                return;
            }
        }
        System.out.println("Фильм с ID " + id + " не найден");
    }

    public void sortMovieDeque() {
        ArrayList<Movie> movieList = new ArrayList<>(movies);
        movieList.sort(Comparator.comparingLong(Movie::getId));
        movies.clear();
        movies.addAll(movieList);
    }

    public void resetId() {
        nextFreeID = 1;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @XmlElementWrapper(name = "movies")
    @XmlElement(name = "movie")
    public ArrayDeque<Movie> getMovies() {
        return movies;
    }

    public String toString() {
        return "Тип ArrayDeque" + "\n" +
                "Создано " + getCreationDate() + "\n" +
                "Количество элементов " + getMovies().size() + "\n";
    }
}
