package UnsereWelt.dto.booking;

import UnsereWelt.dto.seat.TicketDto;
import UnsereWelt.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private Long id;

    private LocalDateTime bookingTime;

    private BookingStatus status;

    @NotNull(message = "User ID must not be null")
    private Long userId;

    @NotNull(message = "Screening ID must not be null")
    private Long screeningId;

    // Optional (nur zur Anzeige – keine Validierung nötig)
    private String userName;
    private String movieTitle;
    private List<TicketDto> tickets;
}
