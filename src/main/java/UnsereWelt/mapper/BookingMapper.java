package UnsereWelt.mapper;

import UnsereWelt.dto.booking.BookingDto;
import UnsereWelt.dto.booking.BookingRequestDto;
import UnsereWelt.dto.seat.TicketDto;
import UnsereWelt.entity.Booking;
import UnsereWelt.entity.User;
import UnsereWelt.entity.Screening;
import UnsereWelt.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {

    public static Booking toEntity(BookingRequestDto dto, User user, Screening screening) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setScreening(screening);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRMED);
        return booking;
    }

    public static BookingDto toDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setBookingTime(booking.getBookingTime());
        dto.setStatus(booking.getStatus());

        if (booking.getUser() != null) {
            dto.setUserId(booking.getUser().getId());
            dto.setUserName(booking.getUser().getName());
        }

        if (booking.getScreening() != null) {
            dto.setScreeningId(booking.getScreening().getId());

            if (booking.getScreening().getMovie() != null) {
                dto.setMovieTitle(booking.getScreening().getMovie().getTitle());
            }
        }

        if (booking.getTickets() != null) {
            List<TicketDto> ticketDtos = booking.getTickets()
                    .stream()
                    .map(TicketMapper::toDto)
                    .collect(Collectors.toList());
            dto.setTickets(ticketDtos);
        }

        return dto;
    }
}
