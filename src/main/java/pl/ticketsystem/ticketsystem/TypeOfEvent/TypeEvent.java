package pl.ticketsystem.ticketsystem.TypeOfEvent;

import pl.ticketsystem.ticketsystem.Event.Event;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="TypeEvent")
public class TypeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeEvent;
    private String nameTypeEvent;
    private Integer minAgeLimit;

    @OneToMany(mappedBy = "typeEvent")
    private Set<Event> event;






}
