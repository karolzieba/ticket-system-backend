package pl.ticketsystem.ticketsystem.Config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Account.AccountRepository;

@Service
@AllArgsConstructor
public class AccountDetalisService implements UserDetailsService {


    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String accountLogin) throws UsernameNotFoundException {


        Account account = accountRepository.findByaccountLogin(accountLogin);


        return new AccountDetalis(account);

    }
}
