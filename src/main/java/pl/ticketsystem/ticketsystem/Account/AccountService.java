package pl.ticketsystem.ticketsystem.Account;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Register.RoleUser;
import pl.ticketsystem.ticketsystem.Register.RoleUserRepository;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleUserRepository roleUserRepository;

    @PostConstruct
    public void addFirstAccount()
    {

        /*RoleUser roleUser = new RoleUser("USER");
        roleUserRepository.save(roleUser);
        RoleUser roleUserSave = roleUserRepository.findById(1).get();
        Set<RoleUser> roles = new HashSet<>();
        roles.add(roleUserSave);
        Account account = new Account("rogal99@gmail.com", "rogal99", "rogal");


        account.setRoles(roles);
        account.setPasswordAccount(passwordEncoder.encode(account.getPasswordAccount()));
        accountRepository.save(account);*/




    }


}
