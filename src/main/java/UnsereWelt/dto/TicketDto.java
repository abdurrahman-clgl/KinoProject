package UnsereWelt.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Long id;

    @NotNull(message = "Booking ID must not be null")
    private Long bookingId;

    @NotNull(message = "Seat ID must not be null")
    private Long seatId;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    // Optional, wird nur zurückgegeben
    private String seatDisplay;
}
