package UnsereWelt.service;

import UnsereWelt.dto.TicketDto;
import UnsereWelt.entity.Booking;
import UnsereWelt.entity.Seat;
import UnsereWelt.entity.Ticket;
import UnsereWelt.mapper.TicketMapper;
import UnsereWelt.repository.BookingRepository;
import UnsereWelt.repository.SeatRepository;
import UnsereWelt.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    public TicketService(TicketRepository ticketRepository,
                         BookingRepository bookingRepository,
                         SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
    }

    public TicketDto createTicket(TicketDto dto) {
        Booking booking = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        Seat seat = seatRepository.findById(dto.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));


        boolean seatTaken = ticketRepository.existsBySeatIdAndBooking_Screening_Id(
                seat.getId(), booking.getScreening().getId()
        );

        if (seatTaken) {
            throw new IllegalStateException("This seat is already booked for the selected screening.");
        }

        Ticket ticket = TicketMapper.toEntity(dto, booking, seat);
        ticket = ticketRepository.save(ticket);

        return TicketMapper.toDto(ticket);
    }

    public List<TicketDto> getTicketsByBooking(Long bookingId) {
        return ticketRepository.findByBookingId(bookingId).stream()
                .map(TicketMapper::toDto)
                .collect(Collectors.toList());
    }
    public boolean isSeatTaken(Long seatId, Long screeningId) {
        return ticketRepository.existsBySeatIdAndBooking_Screening_Id(seatId, screeningId);
    }


}
