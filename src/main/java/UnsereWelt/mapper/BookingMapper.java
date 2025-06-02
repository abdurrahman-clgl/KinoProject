package UnsereWelt.mapper;

import UnsereWelt.dto.BookingDto;
import UnsereWelt.entity.Booking;
import UnsereWelt.entity.User;
import UnsereWelt.entity.Screening;

public class BookingMapper {

    public static Booking toEntity(BookingDto dto, User user, Screening screening) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setBookingTime(dto.getBookingTime());
        booking.setStatus(dto.getStatus());
        booking.setUser(user);
        booking.setScreening(screening);
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

        return dto;
    }
}
