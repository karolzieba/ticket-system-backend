package pl.ticketsystem.ticketsystem.Account;

import org.springframework.web.bind.annotation.CrossOrigin;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Moderator.Moderator;
import pl.ticketsystem.ticketsystem.Role.Role;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Account")
@CrossOrigin(origins = "http://localhost:3000")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccount;
    private String emailAccount;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "account")
    private Client client;

    @OneToOne(mappedBy = "account")
    private Agency agency;

    @OneToOne(mappedBy = "account")
    private Moderator moderator;

    public Account() { }

    public Account(String emailAccount, String username, String password, Role role) {
        this.emailAccount = emailAccount;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
