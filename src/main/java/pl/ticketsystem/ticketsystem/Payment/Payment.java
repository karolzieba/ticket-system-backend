package pl.ticketsystem.ticketsystem.Payment;

import pl.ticketsystem.ticketsystem.Ticket.Ticket;
import pl.ticketsystem.ticketsystem.Type.TypePayment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPayment;
    private LocalDateTime startDatePayment;
    private LocalDateTime endDatePayment;

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

    public LocalDateTime getStartDatePayment() {
        return startDatePayment;
    }

    public void setStartDatePayment(LocalDateTime startDatePayment) {
        this.startDatePayment = startDatePayment;
    }

    public LocalDateTime getEndDatePayment() {
        return endDatePayment;
    }

    public void setEndDatePayment(LocalDateTime endDatePayment) {
        this.endDatePayment = endDatePayment;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }
}
