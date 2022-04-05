package pl.ticketsystem.ticketsystem.Ticket;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }
    public Optional<Ticket> getTicket(long id) {
        return ticketRepository.findById(id);
    }
    public void addTicket(Ticket ticket) {
        if(!Objects.isNull(ticket.getDateTicketBuy())) {
            ticketRepository.save(ticket);
        }
    }

    public void updateTicket(long id, Ticket ticket) {
        if(ticketRepository.existsById(id)) {
            Ticket updatedTicket = ticketRepository.findById(id).orElseThrow(() -> new IllegalStateException("Ticket with this ID does not exist!"));

            if(!Objects.isNull(ticket.getClient())) {
                updatedTicket.setClient(ticket.getClient());
            }

            ticketRepository.save(updatedTicket);
        }
        else {
            throw new IllegalStateException("Ticket with this ID does not exist!");
        }
    }
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }
}
