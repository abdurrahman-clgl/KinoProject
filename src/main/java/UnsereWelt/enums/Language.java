package UnsereWelt.enums;

public enum Language {
    DE("Deutsch"),
    EN("Englisch"),
    FR("Französisch");

    private final String label;

    Language(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
