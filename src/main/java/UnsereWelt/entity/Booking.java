package UnsereWelt.entity;

import UnsereWelt.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @NotNull(message = "Booking time cannot be null")
   private LocalDateTime bookingTime;

   @NotNull(message = "Status cannot be null")
   @Enumerated(EnumType.STRING)
   private BookingStatus status;

   @NotNull(message = "User cannot be null")
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @NotNull(message = "Screening cannot be null")
   @ManyToOne(optional = false)
   @JoinColumn(name = "screening_id", nullable = false)
   private Screening screening;

}
