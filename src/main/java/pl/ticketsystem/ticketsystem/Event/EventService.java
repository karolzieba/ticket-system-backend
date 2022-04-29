package pl.ticketsystem.ticketsystem.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Agency.AgencyRepository;
import pl.ticketsystem.ticketsystem.Type.TypeEvent;
import pl.ticketsystem.ticketsystem.Type.TypeEventRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TypeEventRepository typeEventRepository;
    private final AgencyRepository agencyRepository;
    @Autowired
    public EventService(EventRepository eventRepository, TypeEventRepository typeEventRepository, AgencyRepository agencyRepository) {
        this.eventRepository = eventRepository;
        this.typeEventRepository = typeEventRepository;
        this.agencyRepository = agencyRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
    public Optional<Event> getEvent(long id) {
        return eventRepository.findById(id);
    }
    public void addEvent(Event event) {
        /*if(!Objects.isNull(event.getNameEvent()) &&
                !Objects.isNull(event.getDateTimeEvent()) &&
                !Objects.isNull(event.getLocationEvent()) &&
                !Objects.isNull(event.getPriceEvent()) &&
                !Objects.isNull(event.getCapacityEvent()) //&&
                *//*!Objects.isNull(event.getAgency()) &&
                !Objects.isNull(event.getTypeEvent())*//*) {
            eventRepository.save(event);*/
        TypeEvent typeEvent = typeEventRepository.findBynameTypeEvent(event.getTypeEvent().getNameTypeEvent());
        Agency agency = agencyRepository.findById(event.getAgency().getIdAgency()).get();
        //System.out.println(event.getTypeEvent().getNameTypeEvent());
        //TypeEvent typeEvent = typeEventRepository.findById(event.getTypeEvent().getIdTypeEvent()).get();


        System.out.println(typeEvent.toString());
        System.out.println(agency.toString());
        event.setTypeEvent(typeEvent);
        event.setAgency(agency);

        eventRepository.save(event);



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
