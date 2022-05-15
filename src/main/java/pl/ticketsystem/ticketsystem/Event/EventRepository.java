package pl.ticketsystem.ticketsystem.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ticketsystem.ticketsystem.Type.TypeEvent.TypeEvent;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT idEvent FROM Event WHERE nameEvent = :name AND locationEvent = :location AND priceEvent = :price")
    long findIdEventByNameEvent(@Param("name") String nameEvent, @Param("location") String location,
                                @Param("price") double price);
    List<Event> findEventByTypeEvent(TypeEvent typeEvent);
    List <Event> findAllByOrderByIdEventDesc();
    Optional<Event> findEventByTicket_IdTicket(long idTicket);
}
