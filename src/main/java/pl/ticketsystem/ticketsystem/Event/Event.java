package pl.ticketsystem.ticketsystem.Event;

import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Ticket.Ticket;
import pl.ticketsystem.ticketsystem.TypeOfEvent.TypeEvent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Event")
public class Event implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;
    private String nameEvent;
    private String dateEvent;
    private String locationEvent;
    private Double priceEvent;
    private Integer capacityEvent;

    @ManyToOne
    @JoinColumn(name="idTypeEventFK", referencedColumnName = "idTypeEvent")
    private TypeEvent typeEvent;

    @ManyToOne
    @JoinColumn(name="idAgencyFK", referencedColumnName = "idAgency")
    private Agency agency;

    @OneToMany(mappedBy = "event")
    private Set<Ticket> ticket;


}
