package pl.ticketsystem.ticketsystem.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ticketsystem.ticketsystem.Users.Account.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;



@Entity
@Table(name="user_data")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nameUser;
    private String surName;
    private String dateOfBirth;
    private String phoneNumber;
    private BigDecimal numberAccountBank;
    private Long accountFK;
    @OneToOne
    @JoinColumn(name="accountFK")
    private Account account;

}
