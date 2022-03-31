package pl.ticketsystem.ticketsystem.Account;


import pl.ticketsystem.ticketsystem.Client.Client;


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

    @OneToOne(mappedBy = "account")
    private Client client;






}
