package pl.ticketsystem.ticketsystem.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ticketsystem.ticketsystem.Config.Session;

import java.util.List;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class AccountController {


    private final Session session;
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(Session session, AccountRepository accountRepository) {
        this.session = session;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/index")
    public List<Account> index()
    {



        String username = session.getSessionUserLogin();

        System.out.println(username);

        return accountRepository.findAll();
    }


}
