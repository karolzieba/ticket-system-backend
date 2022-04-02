package pl.ticketsystem.ticketsystem.Payment;

import pl.ticketsystem.ticketsystem.Ticket.Ticket;
import pl.ticketsystem.ticketsystem.Type.TypePayment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPayment;
    private LocalDate startDatePayment;
    private LocalDate endDatePayment;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idTypeOfPaymentFK", referencedColumnName = "idTypePayment")
    private TypePayment typePayment;

    @OneToMany(mappedBy = "payment")
    private Set<Ticket> ticket;

    public long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(long idPayment) {
        this.idPayment = idPayment;
    }

    public LocalDate getStartDatePayment() {
        return startDatePayment;
    }

    public void setStartDatePayment(LocalDate startDatePayment) {
        this.startDatePayment = startDatePayment;
    }

    public LocalDate getEndDatePayment() {
        return endDatePayment;
    }

    public void setEndDatePayment(LocalDate endDatePayment) {
        this.endDatePayment = endDatePayment;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }
}
