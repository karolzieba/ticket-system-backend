package pl.ticketsystem.ticketsystem.TypeOfPayment;

import pl.ticketsystem.ticketsystem.Payment.Payment;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="TypePayment")
public class TypePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypePayment;
    private String nameTypePayment;

    @OneToMany(mappedBy = "typePayment")
    private Set<Payment> payment;
}
