package pl.ticketsystem.ticketsystem.Agency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository <Agency, Long> {
    boolean existsByNIP(String NIP);
    Optional<Agency> getAgencyByAccount_IdAccount(long idAccount);
}
