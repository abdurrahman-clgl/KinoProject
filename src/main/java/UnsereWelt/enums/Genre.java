package UnsereWelt.enums;

public enum Genre {
    ACTION("Action"),
    DRAMA("Drama"),
    KOMÖDIE("Komödie"),
    SCI_FI("Science Fiction"),
    HORROR("Horror"),
    ANIMATION("Animation"),
    FAMILY("Familie"),
    ADVENTURE("Abenteuer"),
    FANTASY("Fantasy"),
    WESTERN("Western");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
