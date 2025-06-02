package UnsereWelt.mapper;

import UnsereWelt.dto.TicketDto;
import UnsereWelt.entity.Booking;
import UnsereWelt.entity.Seat;
import UnsereWelt.entity.Ticket;

public class TicketMapper {

    public static TicketDto toDto(Ticket ticket) {
        if (ticket == null) return null;

        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setBookingId(ticket.getBooking().getId());
        dto.setSeatId(ticket.getSeat().getId());
        dto.setPrice(ticket.getPrice());

        // Optional für lesbare Anzeige im Frontend
        dto.setSeatDisplay(ticket.getSeat().getRowLabel() + ticket.getSeat().getSeatNumber());

        return dto;
    }

    public static Ticket toEntity(TicketDto dto, Booking booking, Seat seat) {
        if (dto == null || booking == null || seat == null) return null;

        Ticket ticket = new Ticket();
        ticket.setBooking(booking);
        ticket.setSeat(seat);
        ticket.setPrice(dto.getPrice());

        return ticket;
    }
}
