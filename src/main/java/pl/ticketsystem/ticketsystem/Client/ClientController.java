package pl.ticketsystem.ticketsystem.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    public final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping(path="{clientId}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public Client getClient(@PathVariable long clientId) {
        return clientService.getClient(clientId);
    }

    @GetMapping(path="/issocial/{clientId}")
    public boolean isAccountSocial(@PathVariable long clientId) {
        return clientService.isAccountSocial(clientId);
    }

    @PatchMapping(path="{idClient}")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_CLIENT_FACEBOOK', 'ROLE_MODERATOR')")
    public void updateClient(@PathVariable long idClient, @RequestBody Client client) {
        clientService.updateClient(idClient, client);
    }

    @DeleteMapping(path="{idClient}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void deleteClient(@PathVariable long idClient) {
        clientService.deleteClient(idClient);
    }
}
