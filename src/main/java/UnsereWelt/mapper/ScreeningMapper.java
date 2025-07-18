package UnsereWelt.mapper;

import UnsereWelt.dto.movie.ScreeningDto;
import UnsereWelt.entity.Screening;

public class ScreeningMapper {

    public static Screening toEntity(ScreeningDto dto) {
        Screening screening = new Screening();
        screening.setDate(dto.getDate());
        screening.setStartTime(dto.getStartTime());
        screening.setAvailable(dto.isAvailable());
        screening.setHall(dto.getHall()); // 🆕 hinzugefügt
        return screening;
    }

    public static ScreeningDto toDto(Screening screening) {
        return new ScreeningDto(
                screening.getId(),
                screening.getMovie() != null ? screening.getMovie().getId() : null,
                screening.getDate(),
                screening.getHall(),
                screening.getStartTime(),
                screening.isAvailable()
        );
    }
}
