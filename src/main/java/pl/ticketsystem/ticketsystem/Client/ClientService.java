package pl.ticketsystem.ticketsystem.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientService{
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
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
}
