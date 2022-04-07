package pl.ticketsystem.ticketsystem.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {

    public Account findByaccountLogin(String accountLogin);
    boolean existsByAccountLogin(String login);
    boolean existsByEmailAccount(String email);
}
