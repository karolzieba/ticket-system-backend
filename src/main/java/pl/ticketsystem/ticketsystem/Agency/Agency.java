package pl.ticketsystem.ticketsystem.Agency;

import pl.ticketsystem.ticketsystem.Account.Account;
import pl.ticketsystem.ticketsystem.Client.Client;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "Agency")
public class Agency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgency;
    private String nameCompany;
    private String NIP;
    private String numberPhone;

    @OneToOne
    @JoinColumn(name="idClientFK", referencedColumnName = "idClient")
    private Client client;
}
