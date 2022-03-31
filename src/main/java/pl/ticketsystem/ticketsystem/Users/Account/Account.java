package pl.ticketsystem.ticketsystem.Users.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ticketsystem.ticketsystem.Users.User;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name="Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;
    private String emailAccount;
    private String accountLogin;
    private String passwordAccount;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private User user;






}
