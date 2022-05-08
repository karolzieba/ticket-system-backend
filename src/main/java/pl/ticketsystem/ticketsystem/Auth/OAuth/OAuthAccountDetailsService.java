package pl.ticketsystem.ticketsystem.Auth.OAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Account.AccountRepository;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Client.ClientRepository;
import pl.ticketsystem.ticketsystem.Role.Role;

import java.nio.charset.Charset;
import java.util.Random;

@Service
public class OAuthAccountDetailsService extends DefaultOAuth2UserService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public OAuthAccountDetailsService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        OAuthAccountDetails accDetails = new OAuthAccountDetails(user.getAuthorities(), user.getAttributes(), user.getName());

        Client client;
        if(accountRepository.existsByIdSocial(accDetails.getIdSocial())) {
            client = updateAccount(accDetails);
        }
        else {
            client = registerAccount(accDetails);
        }

        return new OAuthAccountDetails(client.getAccount().getRole().getGrantedAuthorities(),
                accDetails.getAttributes(),
                client.getAccount().getUsername());
    }

    public Client registerAccount(OAuthAccountDetails accDetails) {
        Account account = new Account();
        Client client = new Client();
        account.setIdSocial(accDetails.getIdSocial());
        account.setUsername(accDetails.getName() + "#" + (accountRepository.findMaxID() + 1));

        byte[] array = new byte[32];
        new Random().nextBytes(array);
        String generatedPassword = new String(array, Charset.forName("UTF-8"));

        account.setPassword(generatedPassword);
        account.setEmailAccount(accDetails.getEmail());
        account.setRole(Role.CLIENT_FACEBOOK);

        String name = accDetails.getName();
        int id = name.indexOf(" ");
        String firstName = name.substring(0, id);
        String surName = name.substring(id + 1, name.length());

        client.setAccount(account);
        client.setNameUser(firstName);
        client.setSurName(surName);

        clientRepository.save(client);

        return client;
    }

    public Client updateAccount(OAuthAccountDetails accDetails) {
        Client client = clientRepository.findByAccount_IdSocial(accDetails.getIdSocial()).orElseThrow(() -> new IllegalStateException("Account with this Social ID does not exist!"));

        String userName = client.getAccount().getUsername();
        int idChar = userName.indexOf("#");
        String idAccount = userName.substring(idChar, userName.length());
        userName = accDetails.getName() + idAccount;

        client.getAccount().setUsername(userName);
        client.getAccount().setEmailAccount(accDetails.getEmail());

        String name = accDetails.getName();
        int idChar2 = name.indexOf(" ");
        String firstName = name.substring(0, idChar2);
        String surName = name.substring(idChar2 + 1, name.length());

        client.setNameUser(firstName);
        client.setSurName(surName);

        clientRepository.save(client);

        return client;
    }
}
