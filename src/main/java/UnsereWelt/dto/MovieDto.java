package UnsereWelt.dto;

import UnsereWelt.enums.AgeRating;
import UnsereWelt.enums.Language;
import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;

    @NotBlank(message = "Genre cannot be blank")
    @Size(max = 50, message = "Genre must be less than 50 characters")
    private String genre;

    @NotNull(message = "Release date is required")
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @NotNull(message = "Age rating must be provided")
    private AgeRating ageRating;

    @NotNull(message = "Language must be provided")
    private Language language;


    @Size(max = 500, message = "Image URL must be less than 500 characters")
    private String imageUrl;


}
