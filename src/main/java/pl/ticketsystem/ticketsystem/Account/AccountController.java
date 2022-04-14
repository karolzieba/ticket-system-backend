package pl.ticketsystem.ticketsystem.Account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class AccountController {



    @GetMapping("/index")
    public String index()
    {

        return "Hello world!";
    }


}
