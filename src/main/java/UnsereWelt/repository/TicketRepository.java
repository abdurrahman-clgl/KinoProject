package UnsereWelt.repository;

import UnsereWelt.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByBookingId(Long bookingId);
    boolean existsBySeatIdAndBooking_Screening_Id(Long seatId, Long screeningId);

}
