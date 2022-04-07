package pl.ticketsystem.ticketsystem.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping(path="{ticketId}")
    public Ticket getTicket(@PathVariable long ticketId) {
        return ticketService.getTicket(ticketId).orElse(null);
    }

    @PostMapping
    public void addTicket(@RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
    }

    @PatchMapping(path="{ticketId}")
    public void updateTicket(@PathVariable long ticketId, @RequestBody Ticket ticket) {
        ticketService.updateTicket(ticketId, ticket);
    }

    @DeleteMapping(path="{ticketId}")
    public void deleteTicket(@PathVariable long ticketId) { ticketService.deleteTicket(ticketId); }
}
