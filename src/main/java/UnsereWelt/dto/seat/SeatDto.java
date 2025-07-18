package UnsereWelt.dto.seat;

import UnsereWelt.enums.SeatCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {

    private Long id;

    @NotBlank(message = "Row label must not be blank")
    private String rowLabel;

    @Min(value = 1, message = "Seat number must be at least 1")
    private int seatNumber;

    @NotNull(message = "Seat category must be provided")
    private SeatCategory category;
}
