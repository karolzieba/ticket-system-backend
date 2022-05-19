package pl.ticketsystem.ticketsystem.Agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Event.Event;
import pl.ticketsystem.ticketsystem.Event.EventRepository;
import pl.ticketsystem.ticketsystem.Payment.PaymentRepository;
import pl.ticketsystem.ticketsystem.Ticket.Ticket;
import pl.ticketsystem.ticketsystem.Ticket.TicketRepository;

import java.util.List;
import java.util.Objects;

@Service
public class AgencyService {
    private final AgencyRepository agencyRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AgencyService(AgencyRepository agencyRepository, EventRepository eventRepository,
                         TicketRepository ticketRepository, PaymentRepository paymentRepository,
                         PasswordEncoder passwordEncoder) {
        this.agencyRepository = agencyRepository;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.paymentRepository = paymentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Agency> getAgencies() {
        return agencyRepository.findAll();
    }

    public Agency getAgency(long agencyId) {
        return agencyRepository.findById(agencyId).get();
    }

    public void updateAgency(long idAgency, Agency agency) {
        if(agencyRepository.existsById(idAgency)) {
            Agency updatedAgency = agencyRepository.findById(idAgency).orElseThrow(() -> new IllegalStateException("Agency with this ID does not exist!"));

            if(!Objects.isNull(agency.getAccount().getUsername())) {
                updatedAgency.getAccount().setUsername(agency.getAccount().getUsername());
            }

            if(!Objects.isNull(agency.getAccount().getEmailAccount())) {
                updatedAgency.getAccount().setEmailAccount(agency.getAccount().getEmailAccount());
            }

            if(!Objects.isNull(agency.getAccount().getPassword())) {
                updatedAgency.getAccount().setPassword(passwordEncoder.encode(agency.getAccount().getPassword()));
            }

            if(!Objects.isNull(agency.getNameCompany())) {
                updatedAgency.setNameCompany(agency.getNameCompany());
            }

            if(!Objects.isNull(agency.getNIP())) {
                updatedAgency.setNIP(agency.getNIP());
            }

            if(!Objects.isNull(agency.getNumberPhone())) {
                updatedAgency.setNumberPhone(agency.getNumberPhone());
            }

            agencyRepository.save(updatedAgency);
        }
        else {
            throw new IllegalStateException("Agency with this ID does not exist!");
        }
    }

    public void deleteAgency(long idAgency) {
        Agency agency = agencyRepository.findById(idAgency).get();

        List<Event> events = eventRepository.findEventsByAgency_IdAgency(idAgency);

        for(Event event : events) {
            List<Ticket> tickets = ticketRepository.getTicketsByEvent_IdEvent(event.getIdEvent());

            ticketRepository.deleteAll(tickets);

            for(Ticket ticket : tickets) {
                paymentRepository.deleteById(ticket.getPayment().getIdPayment());
            }
        }

        eventRepository.deleteAll(events);
        agencyRepository.delete(agency);
    }
}
