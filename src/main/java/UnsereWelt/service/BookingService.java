package UnsereWelt.service;

import UnsereWelt.dto.booking.BookingDto;
import UnsereWelt.dto.booking.BookingRequestDto;
import UnsereWelt.dto.seat.TicketDto;
import UnsereWelt.entity.*;
import UnsereWelt.enums.BookingStatus;
import UnsereWelt.mapper.BookingMapper;
import UnsereWelt.repository.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          ScreeningRepository screeningRepository,
                          SeatRepository seatRepository,
                          TicketRepository ticketRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.screeningRepository = screeningRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
    }

    // 1. Neue Buchung erstellen (angepasst!)
    public BookingDto createBooking(BookingRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Screening screening = screeningRepository.findById(dto.getScreeningId())
                .orElseThrow(() -> new IllegalArgumentException("Screening not found"));

        List<Seat> seats = seatRepository.findAllById(dto.getSeatIds());

        for (Seat seat : seats) {
            if (ticketRepository.existsByScreeningAndSeat(screening, seat)) {
                throw new IllegalStateException("Seat already booked: " + seat.getId());
            }
        }

        Booking booking = BookingMapper.toEntity(dto, user, screening);
        booking = bookingRepository.save(booking);

        List<TicketDto> ticketDtos = new ArrayList<>();

        for (Seat seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setBooking(booking);
            ticket.setSeat(seat);
            ticket.setScreening(screening);
            ticket.setPrice(seat.getPrice()); // oder berechnet
            Ticket saved = ticketRepository.save(ticket);

            ticketDtos.add(new TicketDto(
                    saved.getId(),
                    booking.getId(),
                    seat.getId(),
                    saved.getPrice(),
                    seat.getLabel()
            ));
        }

        BookingDto response = BookingMapper.toDto(booking);
        response.setTickets(ticketDtos);

        return response;
    }

    // 2. Buchungen eines Benutzers abrufen
    public List<BookingDto> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }

    // 3. Buchung stornieren
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
}
