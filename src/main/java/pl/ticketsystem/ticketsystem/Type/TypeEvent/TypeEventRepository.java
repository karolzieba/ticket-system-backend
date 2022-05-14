package pl.ticketsystem.ticketsystem.Type.TypeEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ticketsystem.ticketsystem.Type.TypeEvent.TypeEvent;

public interface TypeEventRepository extends JpaRepository <TypeEvent, Long > {
    TypeEvent findBynameTypeEventIgnoreCase(String name);
}
