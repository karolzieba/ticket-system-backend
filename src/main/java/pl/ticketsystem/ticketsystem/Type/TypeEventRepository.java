package pl.ticketsystem.ticketsystem.Type;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeEventRepository extends JpaRepository <TypeEvent, Long > {

    public TypeEvent findBynameTypeEventIgnoreCase(String name);
}
