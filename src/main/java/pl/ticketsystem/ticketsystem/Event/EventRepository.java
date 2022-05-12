package pl.ticketsystem.ticketsystem.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ticketsystem.ticketsystem.Type.TypeEvent;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    public List<Event> findEventByTypeEvent(TypeEvent typeEvent);
    public List <Event> findAllByOrderByIdEventDesc();


}
