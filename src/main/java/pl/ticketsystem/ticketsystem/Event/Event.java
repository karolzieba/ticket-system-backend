package pl.ticketsystem.ticketsystem.Event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Ticket.Ticket;
import pl.ticketsystem.ticketsystem.Type.TypeEvent.TypeEvent;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="Event")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEvent;
    private String nameEvent;
    private LocalDateTime dateTimeEvent;
    private String locationEvent;
    private Double priceEvent;
    private Integer capacityEvent;
    private boolean waitingToAccept;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "idTypeEventFK", referencedColumnName = "idTypeEvent")
    private TypeEvent typeEvent;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "idAgencyFK", referencedColumnName = "idAgency")
    private Agency agency;

    @OneToMany(mappedBy = "event", orphanRemoval = true)
    @JsonBackReference
    private Set<Ticket> ticket;

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public LocalDateTime getDateTimeEvent() {
        return dateTimeEvent;
    }

    public void setDateTimeEvent(LocalDateTime dateTimeEvent) {
        this.dateTimeEvent = dateTimeEvent;
    }

    public String getLocationEvent() {
        return locationEvent;
    }

    public void setLocationEvent(String locationEvent) {
        this.locationEvent = locationEvent;
    }

    public Double getPriceEvent() {
        return priceEvent;
    }

    public void setPriceEvent(Double priceEvent) {
        this.priceEvent = priceEvent;
    }

    public Integer getCapacityEvent() {
        return capacityEvent;
    }

    public void setCapacityEvent(Integer capacityEvent) {
        this.capacityEvent = capacityEvent;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Set<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }

    public boolean isWaitingToAccept() {
        return waitingToAccept;
    }

    public void setWaitingToAccept(boolean waitingToAccept) {
        this.waitingToAccept = waitingToAccept;
    }
}
