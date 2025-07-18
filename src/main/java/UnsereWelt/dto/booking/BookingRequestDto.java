package UnsereWelt.dto.booking;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Screening ID is required")
    private Long screeningId;

    @NotEmpty(message = "At least one seat must be selected")
    private List<Long> seatIds;
}
