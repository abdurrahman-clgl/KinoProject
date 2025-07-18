package UnsereWelt.entity;

import UnsereWelt.enums.SeatCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Row label cannot be blank")
    private String rowLabel;

    @Positive(message = "Seat number must be positive")
    private int seatNumber;

    @NotNull(message = "Category cannot be null")
    @Enumerated(EnumType.STRING)
    private SeatCategory category;

    public String getLabel() {
        return rowLabel + seatNumber;
    }

    public BigDecimal getPrice() {
        return category.getPrice();
    }

}
