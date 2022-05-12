package pl.ticketsystem.ticketsystem.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Client.ClientRepository;
import pl.ticketsystem.ticketsystem.Event.Event;
import pl.ticketsystem.ticketsystem.Event.EventRepository;
import pl.ticketsystem.ticketsystem.Payment.Payment;
import pl.ticketsystem.ticketsystem.Payment.PaymentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ClientRepository clientRepository;
    private final EventRepository eventRepository;
    private final PaymentRepository paymentRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository, ClientRepository clientRepository, EventRepository eventRepository, PaymentRepository paymentRepository) {
        this.ticketRepository = ticketRepository;
        this.clientRepository = clientRepository;
        this.eventRepository = eventRepository;
        this.paymentRepository = paymentRepository;
    }

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
                Client client = clientRepository.getClientByAccount_IdAccount(ticket.getClient().getIdClient()).get();
                Event event = eventRepository.findById(ticket.getEvent().getIdEvent()).get();
                Payment payment = paymentRepository.findById(ticket.getPayment().getIdPayment()).get();
                ticket.setClient(client);
                ticket.setEvent(event);
                ticket.setPayment(payment);
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
