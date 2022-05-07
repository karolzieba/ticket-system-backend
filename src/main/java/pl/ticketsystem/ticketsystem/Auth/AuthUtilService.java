package pl.ticketsystem.ticketsystem.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Account.AccountRepository;
import pl.ticketsystem.ticketsystem.Auth.OAuth.OAuthAccountDetails;
import pl.ticketsystem.ticketsystem.Auth.REST.AccountDetails;

import java.util.Collection;
import java.util.Map;

@Service
public class AuthUtilService {
    private final AccountRepository accountRepository;

    @Autowired
    public AuthUtilService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void socialDeauthorize() {

    }

    public Map<String, String> getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = "";
        for(GrantedAuthority authority : authorities) {
            if(authority.getAuthority().startsWith("ROLE_")) {
                role = authority.getAuthority();
            }
        }

        String userName = "";
        if(role.equals("ROLE_CLIENT_FACEBOOK")) {
            OAuthAccountDetails accountDetails = (OAuthAccountDetails) authentication.getPrincipal();
            userName = accountDetails.getName();
        }
        else {
            AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
            userName = accountDetails.getUsername();
        }

        Account account = accountRepository.findAccountByUsername(userName).orElseThrow(() -> new IllegalStateException("Account with this username does not exist!"));
        String id = String.valueOf(account.getIdAccount());

        return Map.of("id", id,
                "username", userName,
                "role", role);
    }
}
