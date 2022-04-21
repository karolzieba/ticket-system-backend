package pl.ticketsystem.ticketsystem.Account;

import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Moderator.Moderator;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccount;
    private String emailAccount;
    private String accountLogin;
    private String passwordAccount;

    @OneToOne(mappedBy = "account")
    private Client client;

    @OneToOne(mappedBy = "account")
    private Agency agency;

    @OneToOne(mappedBy = "account")
    private Moderator moderator;

    public Account() { }

    public Account(String emailAccount, String accountLogin, String passwordAccount) {
        this.emailAccount = emailAccount;
        this.accountLogin = accountLogin;
        this.passwordAccount = passwordAccount;
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

    public String getAccountLogin() {
        return accountLogin;
    }

    public void setAccountLogin(String accountLogin) {
        this.accountLogin = accountLogin;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }
}
