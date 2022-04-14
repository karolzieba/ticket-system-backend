package pl.ticketsystem.ticketsystem.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
