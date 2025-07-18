package UnsereWelt.repository;

import UnsereWelt.entity.Screening;
import UnsereWelt.entity.Seat;
import UnsereWelt.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByBookingId(Long bookingId);
    boolean existsByScreeningAndSeat(Screening screening, Seat seat);

}
