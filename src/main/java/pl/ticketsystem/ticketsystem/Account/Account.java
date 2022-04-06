package pl.ticketsystem.ticketsystem.Account;



import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Moderator.Moderator;
import pl.ticketsystem.ticketsystem.Register.RoleUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Account")
@Data
@NoArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id_user"),
            inverseJoinColumns = @JoinColumn(name = "roles_id_role")
    )
    private Set<RoleUser> roles = new HashSet<>();



    public void setRoles(Set<RoleUser> roles) {

        this.roles = roles;
    }

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
