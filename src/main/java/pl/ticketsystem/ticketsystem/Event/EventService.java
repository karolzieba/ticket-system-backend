package pl.ticketsystem.ticketsystem.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Agency.AgencyRepository;
import pl.ticketsystem.ticketsystem.Client.ClientRepository;
import pl.ticketsystem.ticketsystem.Ticket.TicketRepository;
import pl.ticketsystem.ticketsystem.Type.TypeEvent.TypeEvent;
import pl.ticketsystem.ticketsystem.Type.TypeEvent.TypeEventRepository;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TypeEventRepository typeEventRepository;
    private final AgencyRepository agencyRepository;
    private final TicketRepository ticketRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public EventService(EventRepository eventRepository, TypeEventRepository typeEventRepository, AgencyRepository agencyRepository, TicketRepository ticketRepository, ClientRepository clientRepository) {
        this.eventRepository = eventRepository;
        this.typeEventRepository = typeEventRepository;
        this.agencyRepository = agencyRepository;
        this.ticketRepository = ticketRepository;
        this.clientRepository = clientRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAllByOrderByIdEventDesc();
    }

    public List<Event> getEventsByCategory(String categoryName)
    {

        TypeEvent typeEvent = typeEventRepository.findBynameTypeEventIgnoreCase(categoryName);
        System.out.println("DADADA " + eventRepository.findEventByTypeEvent(typeEvent));
        return eventRepository.findEventByTypeEvent(typeEvent);
    }
    public Optional<Event> getEvent(long id) {
        return eventRepository.findById(id);
    }


    public List<Object[]> getUserEvents(long idAccount)
    {

        List<Object[]> tickets = null;
        tickets = ticketRepository.getClientTicket(idAccount);
        return tickets;

    }


    public long addEvent(Event event) {
        /*if(!Objects.isNull(event.getNameEvent()) &&
                !Objects.isNull(event.getDateTimeEvent()) &&
                !Objects.isNull(event.getLocationEvent()) &&
                !Objects.isNull(event.getPriceEvent()) &&
                !Objects.isNull(event.getCapacityEvent()) //&&
                *//*!Objects.isNull(event.getAgency()) &&
                !Objects.isNull(event.getTypeEvent())*//*) {
            eventRepository.save(event);*/
        TypeEvent typeEvent = typeEventRepository.findBynameTypeEventIgnoreCase(event.getTypeEvent().getNameTypeEvent());
        Agency agency = agencyRepository.findById(event.getAgency().getIdAgency()).get();

        event.setTypeEvent(typeEvent);
        event.setAgency(agency);

        eventRepository.save(event);

        return eventRepository.findIdEventByNameEvent(event.getNameEvent(), event.getLocationEvent(), event.getPriceEvent());
    }

    public void addEventImage(MultipartFile image) {
        System.out.println("Nazwa pliku: " + image.getOriginalFilename());
        if(!Objects.isNull(image)) {
            File file = new File("src/main/resources/img/" + image.getOriginalFilename());

            try (OutputStream os = new FileOutputStream(file)) {
                os.write(image.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateEvent(long id, Event event) {
        if(eventRepository.existsById(id)) {
            Event updatedEvent = eventRepository.findById(id).orElseThrow(() -> new IllegalStateException("Event with this ID does not exist!"));

            if(!Objects.isNull(event.getNameEvent())) {
                updatedEvent.setNameEvent(event.getNameEvent());
            }

            if(!Objects.isNull(event.getDateTimeEvent())) {
                updatedEvent.setDateTimeEvent(event.getDateTimeEvent());
            }

            if(!Objects.isNull(event.getLocationEvent())) {
                updatedEvent.setLocationEvent(event.getLocationEvent());
            }

            if(!Objects.isNull(event.getPriceEvent())) {
                updatedEvent.setPriceEvent(event.getPriceEvent());
            }

            if(!Objects.isNull(event.getCapacityEvent())) {
                updatedEvent.setCapacityEvent(event.getCapacityEvent());
            }

            if(!Objects.isNull(event.isWaitingToAccept())) {
                updatedEvent.setWaitingToAccept(event.isWaitingToAccept());
            }

            if(!Objects.isNull(event.getAgency())) {
                updatedEvent.setAgency(event.getAgency());
            }

            if(!Objects.isNull(event.getTypeEvent())) {
                updatedEvent.setTypeEvent(event.getTypeEvent());
            }

            eventRepository.save(updatedEvent);
        }
        else {
            throw new IllegalStateException("Event with this ID does not exist!");
        }
    }
    public void deleteEvent(long id) {

        eventRepository.deleteById(id);
    }
}
