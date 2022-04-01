package pl.ticketsystem.ticketsystem.Client;



import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Agency.Agency;
import pl.ticketsystem.ticketsystem.Moderator.Moderator;
import pl.ticketsystem.ticketsystem.Ticket.Ticket;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name="Client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String nameUser;
    private String surName;
    private String dateOfBirth;
    private String phoneNumber;
    private BigDecimal numberAccountBank;

    @OneToOne
    @JoinColumn(name="account_FK", referencedColumnName = "idAccount")
    private Account account;


    @OneToOne(mappedBy = "client")
    private Moderator moderator;

    @OneToOne(mappedBy = "client")
    private Agency agency;

    @OneToMany(mappedBy = "client")
    private Set<Ticket> ticket;
}
