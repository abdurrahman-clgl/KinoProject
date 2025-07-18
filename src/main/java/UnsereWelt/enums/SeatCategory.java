package UnsereWelt.enums;

import java.math.BigDecimal;

public enum SeatCategory {
    REGULAR(new BigDecimal("10.00")),
    PREMIUM(new BigDecimal("15.00")),
    VIP(new BigDecimal("20.00"));

    private final BigDecimal price;

    SeatCategory(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}