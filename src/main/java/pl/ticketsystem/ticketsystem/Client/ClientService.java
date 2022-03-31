package pl.ticketsystem.ticketsystem.Client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService{

    ClientRepository clientRepository;


    public void addUserToData()
    {

        //clientRepository.save(new User("Daniel", "Rogowski", "1999-08-11", "123-456-789", new BigDecimal(4213), 1L));
    }


}
