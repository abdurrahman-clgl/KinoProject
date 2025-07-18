package UnsereWelt.entity;

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
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Booking cannot be null")
    @ManyToOne
    private Booking booking;

    @NotNull(message = "Seat cannot be null")
    @ManyToOne
    private Seat seat;

    @NotNull(message = "Screening cannot be null")
    @ManyToOne
    private Screening screening;

    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price must be positive or zero")
    private BigDecimal price;


}
