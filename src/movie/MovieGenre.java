package movie;

public enum MovieGenre {
    ACTION("Action"),
    COMEDY("Comdey"),
    SCIENCE_FICTION("Science fiction");

    private final String value;

    MovieGenre(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}