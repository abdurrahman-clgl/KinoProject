package UnsereWelt.controller;

import UnsereWelt.dto.ScreeningDto;
import UnsereWelt.service.ScreeningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/screenings")
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }


    @GetMapping
    public ResponseEntity<List<ScreeningDto>> getAllScreenings() {
        return ResponseEntity.ok(screeningService.getAllScreenings());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ScreeningDto> getScreeningById(@PathVariable Long id) {
        return ResponseEntity.ok(screeningService.getScreeningById(id));
    }


    @PostMapping
    public ResponseEntity<ScreeningDto> addScreening(@Valid @RequestBody ScreeningDto dto) {
        return ResponseEntity.ok(screeningService.addScreening(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ScreeningDto> updateScreening(@PathVariable Long id, @Valid @RequestBody ScreeningDto dto) {
        return ResponseEntity.ok(screeningService.updateScreening(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
