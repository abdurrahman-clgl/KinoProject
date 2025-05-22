package UnsereWelt.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import UnsereWelt.enums.AgeRating;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(nullable = false, length = 50)
    private String genre;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false, length = 50)
    private String language;

    @Column(length = 500)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgeRating ageRating;



}
