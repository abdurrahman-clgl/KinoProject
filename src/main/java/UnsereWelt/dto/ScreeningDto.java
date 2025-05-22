package UnsereWelt.dto;

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

    @NotBlank(message = "Movie title cannot be blank")
    private String movieTitle;

    @NotNull(message = "Date must be provided")
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDate date;

    @NotNull(message = "Start time must be provided")
    private LocalTime startTime;

    private boolean available;


}




