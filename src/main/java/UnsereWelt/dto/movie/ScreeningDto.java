package UnsereWelt.dto.movie;

import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScreeningDto {

    private Long id;

    @NotNull(message = "Movie ID must not be null")
    private Long movieId;

    @NotNull(message = "Date must be provided")
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;
    @NotBlank(message = "Hall must be provided")
    private String hall;

    @NotNull(message = "Start time must be provided")
    private LocalTime startTime;

    private boolean available;


}




