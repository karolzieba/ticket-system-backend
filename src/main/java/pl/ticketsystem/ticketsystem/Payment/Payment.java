package pl.ticketsystem.ticketsystem.Payment;

import pl.ticketsystem.ticketsystem.Ticket.Ticket;
import pl.ticketsystem.ticketsystem.TypeOfPayment.TypePayment;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;
    private Date startDatePayment;
    private Date endDatePayment;

    @ManyToOne
    @JoinColumn(name = "idTypeOfPaymentFK", referencedColumnName = "idTypePayment")
    private TypePayment typePayment;

    @OneToMany(mappedBy = "payment")
    private Set<Ticket> ticket;
}
