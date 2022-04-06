package pl.ticketsystem.ticketsystem.Client;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Ticket.Ticket;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name="Client")
@JsonIgnoreProperties("account")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClient;
    private String nameUser;
    private String surName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String numberAccountBank;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="account_FK", referencedColumnName = "idAccount")
    private Account account;

    @OneToMany(mappedBy = "client")
    private Set<Ticket> ticket;

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumberAccountBank() {
        return numberAccountBank;
    }

    public void setNumberAccountBank(String numberAccountBank) {
        this.numberAccountBank = numberAccountBank;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
