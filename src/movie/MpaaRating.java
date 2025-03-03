package movie;

public enum MpaaRating {
    G("G"),
    PG("PG"),
    PG_13("PG 13"),
    NC_17("NC 17");

    private final String value;

    MpaaRating(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}