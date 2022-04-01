package pl.ticketsystem.ticketsystem.TypeOfEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeEventRepository extends JpaRepository <TypeEvent, Long> {
}
