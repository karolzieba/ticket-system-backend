package pl.ticketsystem.ticketsystem.Account;



import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ticketsystem.ticketsystem.Client.Client;
import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name="Account")
@Data
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;
    private String emailAccount;
    private String accountLogin;
    private String passwordAccount;

    @OneToOne(mappedBy = "account")
    private Client client;

    public Account(String emailAccount, String accountLogin, String passwordAccount) {
        this.emailAccount = emailAccount;
        this.accountLogin = accountLogin;
        this.passwordAccount = passwordAccount;
    }


}
