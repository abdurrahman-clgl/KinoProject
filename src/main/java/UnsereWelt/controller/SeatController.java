package UnsereWelt.controller;

import UnsereWelt.dto.SeatDto;
import UnsereWelt.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }


    @PostMapping
    public ResponseEntity<SeatDto> createSeat(@RequestBody @Valid SeatDto dto) {
        SeatDto created = seatService.createSeat(dto);
        return ResponseEntity.ok(created);
    }


    @GetMapping
    public ResponseEntity<List<SeatDto>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }
}
