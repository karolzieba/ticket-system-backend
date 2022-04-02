package pl.ticketsystem.ticketsystem.Type;

import pl.ticketsystem.ticketsystem.Event.Event;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="TypeEvent")
public class TypeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTypeEvent;
    private String nameTypeEvent;
    private int minAgeLimit;

    @OneToMany(mappedBy = "typeEvent")
    private Set<Event> event;

    public long getIdTypeEvent() {
        return idTypeEvent;
    }

    public void setIdTypeEvent(long idTypeEvent) {
        this.idTypeEvent = idTypeEvent;
    }

    public String getNameTypeEvent() {
        return nameTypeEvent;
    }

    public void setNameTypeEvent(String nameTypeEvent) {
        this.nameTypeEvent = nameTypeEvent;
    }

    public int getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(int minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }
}
