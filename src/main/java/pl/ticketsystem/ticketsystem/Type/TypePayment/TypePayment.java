package pl.ticketsystem.ticketsystem.Type.TypePayment;

import pl.ticketsystem.ticketsystem.Payment.Payment;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="TypePayment")
public class TypePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypePayment;
    private String nameTypePayment;

    @OneToMany(mappedBy = "typePayment")
    private Set<Payment> payment;

    public int getIdTypePayment() {
        return idTypePayment;
    }

    public void setIdTypePayment(int idTypePayment) {
        this.idTypePayment = idTypePayment;
    }

    public String getNameTypePayment() {
        return nameTypePayment;
    }

    public void setNameTypePayment(String nameTypePayment) {
        this.nameTypePayment = nameTypePayment;
    }
}
