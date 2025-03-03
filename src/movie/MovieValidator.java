package movie;

import java.time.LocalDate;

public class MovieValidator {

    public boolean validateTitle(String title) {
        return !(title == null) && !title.isEmpty();
    }

    public boolean validateY(Double y) {
        return y != null && y <= 102;
    }

    public boolean validateOscarCount(int oscarCount) {
        return oscarCount > 0;
    }

    public boolean validateGenre(MovieGenre genre) {
        return genre != null;
    }

    public boolean validateDirectorName(String directorName) {
        return !(directorName == null) && !directorName.isEmpty();
    }

    public boolean validateDirectorHeight(int height) {
        return height > 0;
    }

    public boolean validateDirectorWeight(int weight) {
        return weight > 0;
    }



}
