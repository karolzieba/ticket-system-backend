package pl.ticketsystem.ticketsystem.Account;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


   /* @PostConstruct
    public void addFirstAccount()
    {

        Account account = new Account("rogal99@gmail.com", "rogal99", passwordEncoder.encode("rogalik"));
        accountRepository.save(account);
    }*/


}
