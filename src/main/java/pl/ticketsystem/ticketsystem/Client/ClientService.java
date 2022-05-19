package pl.ticketsystem.ticketsystem.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Payment.PaymentRepository;
import pl.ticketsystem.ticketsystem.Ticket.Ticket;
import pl.ticketsystem.ticketsystem.Ticket.TicketRepository;

import java.util.List;
import java.util.Objects;

@Service
public class ClientService{
    private final ClientRepository clientRepository;
    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepository clientRepository, TicketRepository ticketRepository, PaymentRepository paymentRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.ticketRepository = ticketRepository;
        this.paymentRepository = paymentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(long clientId) {
        return clientRepository.findById(clientId).get();
    }

    public void updateClient(long idClient, Client client) {
        if(clientRepository.existsById(idClient)) {
            Client updatedClient = clientRepository.findById(idClient).orElseThrow(() -> new IllegalStateException("Client with this ID does not exist!"));

            if(!Objects.isNull(client.getAccount().getUsername())) {
                updatedClient.getAccount().setUsername(client.getAccount().getUsername());
            }

            if(!Objects.isNull(client.getAccount().getEmailAccount())) {
                updatedClient.getAccount().setEmailAccount(client.getAccount().getEmailAccount());
            }

            if(!Objects.isNull(client.getAccount().getPassword())) {
                updatedClient.getAccount().setPassword(passwordEncoder.encode(client.getAccount().getPassword()));
            }

            if(!Objects.isNull(client.getNameUser())) {
                updatedClient.setNameUser(client.getNameUser());
            }

            if(!Objects.isNull(client.getSurName())) {
                updatedClient.setSurName(client.getSurName());
            }

            if(!Objects.isNull(client.getDateOfBirth())) {
                updatedClient.setDateOfBirth(client.getDateOfBirth());
            }

            if(!Objects.isNull(client.getPhoneNumber())) {
                updatedClient.setPhoneNumber(client.getPhoneNumber());
            }

            if(!Objects.isNull(client.getNumberAccountBank())) {
                updatedClient.setNumberAccountBank(client.getNumberAccountBank());
            }

            clientRepository.save(updatedClient);
        }
        else {
            throw new IllegalStateException("Client with this ID does not exist!");
        }
    }

    public boolean isAccountSocial(long clientId) {
        Client client = clientRepository.findById(clientId).get();

        if(Objects.isNull(client.getAccount().getIdSocial())) {
            return false;
        }

        return true;
    }

    public void deleteClient(long clientId) {
        Client client = clientRepository.findById(clientId).get();

        List<Ticket> tickets = ticketRepository.getTicketsByClient_IdClient(clientId);

        ticketRepository.deleteAll(tickets);

        for(Ticket ticket : tickets) {
            paymentRepository.deleteById(ticket.getPayment().getIdPayment());
        }

        clientRepository.delete(client);
    }
}
