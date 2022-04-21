package pl.ticketsystem.ticketsystem.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Moderator.Moderator;

@RestController
@RequestMapping(path="register")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/moderator")
    public void register(@RequestBody Moderator moderator) {
        registerService.register(moderator);
    }

    @PostMapping("/client")
    public void register(@RequestBody Client client) {
        registerService.register(client);
    }

    @PostMapping("/agency")
    public void register(@RequestBody Agency agency) {
        registerService.register(agency);
    }
}
