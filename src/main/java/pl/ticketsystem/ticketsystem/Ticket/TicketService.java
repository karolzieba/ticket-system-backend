package pl.ticketsystem.ticketsystem.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }
    public Optional<Ticket> getTicket(long id) {
        return ticketRepository.findById(id);
    }
    public void addTicket(Ticket ticket) {
        if(!Objects.isNull(ticket.getDateTicketBuy()) /*&&
                !Objects.isNull(ticket.getClient()) &&
                !Objects.isNull(ticket.getEvent()) &&
                !Objects.isNull(ticket.getPayment())*/) {
            /*if(isAgeCorrect(ticket.getClient().getDateOfBirth(),
                    ticket.getEvent().getDateTimeEvent(),
                    ticket.getEvent().getTypeEvent().getMinAgeLimit())) {*/
                ticketRepository.save(ticket);
            //}
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

    public boolean isAgeCorrect(LocalDate dateOfBirth, LocalDateTime dateEvent, int age) {
        LocalDate event = LocalDate.of(dateEvent.getYear(), dateEvent.getMonth(), dateEvent.getDayOfMonth());

        dateOfBirth = LocalDate.of(dateOfBirth.getYear() + age, dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());

        return dateOfBirth.isBefore(event) || dateOfBirth.isEqual(event);
    }
}
