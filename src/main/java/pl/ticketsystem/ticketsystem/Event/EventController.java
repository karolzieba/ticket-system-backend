package pl.ticketsystem.ticketsystem.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping(path="api/event")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping(path = "/client/orders/{idAccount}")
    public List<Object[]> getClientEvent(@PathVariable long idAccount)
    {

        return eventService.getUserEvents(idAccount);
    }

    @GetMapping(path="{eventId}")
    public Event getEvent(@PathVariable long eventId) {
        return eventService.getEvent(eventId).orElse(null);
    }

    @GetMapping(path="/category/{categoryEvent}")
    public List<Event> getEventCategory(@PathVariable String categoryEvent)
    {
        return eventService.getEventsByCategory(categoryEvent);
    }



    @PostMapping
    @PreAuthorize("hasAuthority('event_add')")
    public ResponseEntity<Long> addEvent(@RequestBody Event event) {
        return new ResponseEntity<>(eventService.addEvent(event), HttpStatus.OK);
    }

    @PostMapping("/image")
    @PreAuthorize("hasAuthority('event_add')")
    public void addEventImage(@RequestParam("image") MultipartFile image) {
        eventService.addEventImage(image);
    }

    @PatchMapping(path="{eventId}")
    @PreAuthorize("hasAuthority('event_update')")
    public void updateEvent(@PathVariable long eventId, @RequestBody Event event) {
        eventService.updateEvent(eventId, event);
    }

    @DeleteMapping(path="{eventId}")
    @PreAuthorize("hasAuthority('event_delete')")
    public void deleteEvent(@PathVariable long eventId) { eventService.deleteEvent(eventId); }
}
