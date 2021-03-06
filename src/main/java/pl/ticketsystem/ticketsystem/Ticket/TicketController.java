package pl.ticketsystem.ticketsystem.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//import pl.ticketsystem.ticketsystem.PayPal.Order;

import java.time.LocalDateTime;
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



    /*@GetMapping(path="{username}")
    @PreAuthorize("hasAuthority('ticket_show')")
    public List<Ticket> getTicketsByUsername(@PathVariable String username) {
        return ticketService.getTicketsByUsername(username);
    }*/


    @GetMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping(path="{ticketId}")
    @PreAuthorize("hasAuthority('ticket_show')")
    public Ticket getTicket(@PathVariable long ticketId) {
        return ticketService.getTicket(ticketId).orElse(null);
    }

    @GetMapping(path="/client/{clientId}")
    @PreAuthorize("hasAuthority('ticket_show')")
    public List<Ticket> getTicketsByClientId(@PathVariable long clientId) {
        return ticketService.getTicketsByClientId(clientId);
    }

    @GetMapping(path="/checkAge")
    @PreAuthorize("hasAuthority('ticket_add')")
    public boolean checkAge(@RequestParam("idclient") long idClient, @RequestParam("idevent") long idEvent) {
        return ticketService.checkAge(idClient, idEvent);
    }

    @GetMapping(path="/checkTicketExist")
    @PreAuthorize("hasAuthority('ticket_add')")
    public boolean checkTicketExist(@RequestParam("idclient") long idClient, @RequestParam("idevent") long idEvent) {
        return ticketService.checkTicketExist(idClient, idEvent);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ticket_add')")
    public void addTicket(@RequestBody Ticket ticket /*,@RequestBody Order order*/) {
        ticketService.addTicket(ticket);
    }

    @PatchMapping(path="{ticketId}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void updateTicket(@PathVariable long ticketId, @RequestBody Ticket ticket) {
        ticketService.updateTicket(ticketId, ticket);
    }

    @DeleteMapping(path="{ticketId}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void deleteTicket(@PathVariable long ticketId) { ticketService.deleteTicket(ticketId); }
}
