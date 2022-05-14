package pl.ticketsystem.ticketsystem.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Client> getClientByAccount_IdAccount(long idAccount);
    Optional<Client> getClientByIdClient(long idClient);
    Optional<Client> findByAccount_IdSocial(String idSocial);
}
