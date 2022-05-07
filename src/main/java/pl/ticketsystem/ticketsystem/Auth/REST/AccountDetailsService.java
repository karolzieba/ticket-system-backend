package pl.ticketsystem.ticketsystem.Auth.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Account.AccountRepository;
import pl.ticketsystem.ticketsystem.Auth.REST.AccountDetails;

@Service
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User with this username was not found!"));

        return new AccountDetails(account.getRole().getGrantedAuthorities(), account.getUsername(), account.getPassword(),
                true, true, true, true);
    }
}
