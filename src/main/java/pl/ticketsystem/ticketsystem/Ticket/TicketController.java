package pl.ticketsystem.ticketsystem.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

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

    @PutMapping(path="{ticketId}")
    public void updateTicket(@PathVariable long ticketId, @RequestBody Ticket ticket) {
        ticketService.updateTicket(ticketId, ticket);
    }

    @DeleteMapping(path="{ticketId}")
    public void deleteTicket(@PathVariable long ticketId) { ticketService.deleteTicket(ticketId); }
}
