package UnsereWelt.service;

import UnsereWelt.dto.BookingDto;
import UnsereWelt.entity.Booking;
import UnsereWelt.entity.Screening;
import UnsereWelt.entity.User;
import UnsereWelt.enums.BookingStatus;
import UnsereWelt.mapper.BookingMapper;
import UnsereWelt.repository.BookingRepository;
import UnsereWelt.repository.ScreeningRepository;
import UnsereWelt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ScreeningRepository screeningRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          ScreeningRepository screeningRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.screeningRepository = screeningRepository;
    }

    // 1. Neue Buchung erstellen
    public BookingDto createBooking(BookingDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Screening screening = screeningRepository.findById(dto.getScreeningId())
                .orElseThrow(() -> new IllegalArgumentException("Screening not found"));

        dto.setBookingTime(LocalDateTime.now());
        dto.setStatus(BookingStatus.CONFIRMED);

        Booking booking = BookingMapper.toEntity(dto, user, screening);
        booking = bookingRepository.save(booking);

        return BookingMapper.toDto(booking);
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
