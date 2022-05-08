package pl.ticketsystem.ticketsystem.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {
    boolean existsByUsername(String login);
    boolean existsByEmailAccount(String email);
    boolean existsByIdSocial(String idSocial);
    Optional<Account> findAccountByUsername(String username);
    @Query("SELECT MAX(idAccount) FROM Account")
    long findMaxID();
}
