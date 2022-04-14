package pl.ticketsystem.ticketsystem.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="api/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping(path="{eventId}")
    public Event getEvent(@PathVariable long eventId) {
        return eventService.getEvent(eventId).orElse(null);
    }

    @PostMapping("/addevent")
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

    @PatchMapping(path="{eventId}")
    public void updateEvent(@PathVariable long eventId, @RequestBody Event event) {
        eventService.updateEvent(eventId, event);
    }

    @DeleteMapping(path="{eventId}")
    public void deleteEvent(@PathVariable long eventId) { eventService.deleteEvent(eventId); }
}
