package UnsereWelt.controller;

import UnsereWelt.dto.seat.TicketDto;
import UnsereWelt.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody @Valid TicketDto dto) {
        TicketDto created = ticketService.createTicket(dto);
        return ResponseEntity.ok(created);
    }


    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<TicketDto>> getTicketsByBooking(@PathVariable Long bookingId) {
        List<TicketDto> tickets = ticketService.getTicketsByBooking(bookingId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> isSeatAvailable(
            @RequestParam Long seatId,
            @RequestParam Long screeningId) {
        boolean taken = ticketService.isSeatTaken(seatId, screeningId);
        return ResponseEntity.ok(!taken);
    }

}
