package pl.ticketsystem.ticketsystem.Moderator;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Client.Client;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "moderator")
public class Moderator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idModerator;
    private String userNameModerator;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="account_FK", referencedColumnName = "idAccount")
    private Account account;

    public long getIdModerator() {
        return idModerator;
    }

    public void setIdModerator(long idModerator) {
        this.idModerator = idModerator;
    }

    public String getUserNameModerator() {
        return userNameModerator;
    }

    public void setUserNameModerator(String userNameModerator) {
        this.userNameModerator = userNameModerator;
    }

    @JsonIgnore
    @JsonProperty(value = "account")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
