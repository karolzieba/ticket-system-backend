package pl.ticketsystem.ticketsystem.Users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserService{

    UserRepository userRepository;


    public void addUserToData()
    {

        //userRepository.save(new User("Daniel", "Rogowski", "1999-08-11", "123-456-789", new BigDecimal(4213), 1L));
    }


}
