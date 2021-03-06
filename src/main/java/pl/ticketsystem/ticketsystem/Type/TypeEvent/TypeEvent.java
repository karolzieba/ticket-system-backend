package pl.ticketsystem.ticketsystem.Type.TypeEvent;

import pl.ticketsystem.ticketsystem.Event.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="TypeEvent")
public class TypeEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
