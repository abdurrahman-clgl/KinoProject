package UnsereWelt.enums;

import jakarta.validation.constraints.NotNull;

public enum AgeRating {
    FSK_6("6+"),
    FSK_12("12+"),
    FSK_16("16+"),
    FSK_18("18+");

    private final String displayName;

    AgeRating(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

