package pl.ticketsystem.ticketsystem.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    public final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PatchMapping(path="{idClient}")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_CLIENT_FACEBOOK', 'ROLE_MODERATOR')")
    public void updateClient(@PathVariable long idClient, @RequestBody Client client) {
        clientService.updateClient(idClient, client);
    }
}
