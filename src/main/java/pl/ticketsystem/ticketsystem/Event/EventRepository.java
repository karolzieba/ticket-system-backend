package pl.ticketsystem.ticketsystem.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ticketsystem.ticketsystem.Type.TypeEvent;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventByTypeEvent(TypeEvent typeEvent);
    @Query("SELECT idEvent FROM Event WHERE nameEvent = :name AND locationEvent = :location AND priceEvent = :price")
    long findIdEventByNameEvent(@Param("name") String nameEvent, @Param("location") String location,
                                @Param("price") double price);
}
