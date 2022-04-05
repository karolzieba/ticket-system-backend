package pl.ticketsystem.ticketsystem.Event;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService {
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
    public Optional<Event> getEvent(long id) {
        return eventRepository.findById(id);
    }
    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public void updateEvent(long id, Event event) {
        if(eventRepository.existsById(id)) {
            Event updatedEvent = eventRepository.findById(id).orElseThrow(() -> new IllegalStateException("Event with this ID does not exist!"));

            if(!Objects.isNull(event.getNameEvent())) {
                updatedEvent.setNameEvent(event.getNameEvent());
            }

            if(!Objects.isNull(event.getDateEvent())) {
                updatedEvent.setDateEvent(event.getDateEvent());
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
