package UnsereWelt.service;

import UnsereWelt.dto.SeatDto;
import UnsereWelt.entity.Seat;
import UnsereWelt.mapper.SeatMapper;
import UnsereWelt.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public SeatDto createSeat(SeatDto dto) {
        Seat seat = SeatMapper.toEntity(dto);
        seat = seatRepository.save(seat);
        return SeatMapper.toDto(seat);
    }

    public List<SeatDto> getAllSeats() {
        return seatRepository.findAll().stream()
                .map(SeatMapper::toDto)
                .collect(Collectors.toList());
    }

    public Seat getSeatById(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
    }
}
