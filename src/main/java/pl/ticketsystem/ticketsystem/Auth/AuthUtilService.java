package pl.ticketsystem.ticketsystem.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Account.AccountRepository;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Agency.AgencyRepository;
import pl.ticketsystem.ticketsystem.Auth.OAuth.OAuthAccountDetails;
import pl.ticketsystem.ticketsystem.Auth.REST.AccountDetails;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Client.ClientRepository;
import pl.ticketsystem.ticketsystem.Moderator.Moderator;
import pl.ticketsystem.ticketsystem.Moderator.ModeratorRepository;

import java.util.Collection;
import java.util.Map;

@Service
public class AuthUtilService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final AgencyRepository agencyRepository;
    private final ModeratorRepository moderatorRepository;

    @Autowired
    public AuthUtilService(AccountRepository accountRepository, ClientRepository clientRepository, AgencyRepository agencyRepository, ModeratorRepository moderatorRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.agencyRepository = agencyRepository;
        this.moderatorRepository = moderatorRepository;
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
            userName = accountDetails.getUsername();
        }
        else {
            AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
            userName = accountDetails.getUsername();
        }

        Account account = accountRepository.findAccountByUsername(userName).orElseThrow(() -> new IllegalStateException("Account with this username does not exist!"));
        long id = account.getIdAccount();

        long idRole = -1;
        if(role.startsWith("ROLE_CLIENT")) {
            Client client = clientRepository.getClientByAccount_IdAccount(id).orElseThrow(() -> new IllegalStateException("Client with this ID does not exist!"));
            idRole = client.getIdClient();
        }
        else if(role.startsWith("ROLE_AGENCY")) {
            Agency agency = agencyRepository.getAgencyByAccount_IdAccount(id).orElseThrow(() -> new IllegalStateException("Agency with this ID does not exist!"));
            idRole = agency.getIdAgency();
        }
        else if(role.startsWith("ROLE_MODERATOR")) {
            Moderator moderator = moderatorRepository.getModeratorByAccount_IdAccount(id).orElseThrow(() -> new IllegalStateException("Moderator with this ID does not exist!"));
            idRole = moderator.getIdModerator();
        }

        return Map.of("id", String.valueOf(id),
                "username", userName,
                "role", role,
                "idRole", String.valueOf(idRole));
    }
}
