package UnsereWelt.mapper;

import UnsereWelt.dto.seat.SeatDto;
import UnsereWelt.entity.Seat;

public class SeatMapper {

    public static SeatDto toDto(Seat seat) {
        if (seat == null) return null;

        return new SeatDto(
                seat.getId(),
                seat.getRowLabel(),
                seat.getSeatNumber(),
                seat.getCategory()
        );
    }

    public static Seat toEntity(SeatDto dto) {
        if (dto == null) return null;

        Seat seat = new Seat();
        seat.setId(dto.getId()); // optional
        seat.setRowLabel(dto.getRowLabel());
        seat.setSeatNumber(dto.getSeatNumber());
        seat.setCategory(dto.getCategory());
        return seat;
    }
}
