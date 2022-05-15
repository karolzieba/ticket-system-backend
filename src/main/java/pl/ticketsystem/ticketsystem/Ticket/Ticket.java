package pl.ticketsystem.ticketsystem.Ticket;

import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Event.Event;
import pl.ticketsystem.ticketsystem.Payment.Payment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="Ticket")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTicket;
    private LocalDateTime dateTicketBuy;

    @ManyToOne
    @JoinColumn(name="idClientFK", referencedColumnName = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name="idEventFK", referencedColumnName = "idEvent")
    private Event event;

    @OneToOne
    @JoinColumn(name="idPaymentFK", referencedColumnName = "idPayment")
    private Payment payment;

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public LocalDateTime getDateTicketBuy() {
        return dateTicketBuy;
    }

    public void setDateTicketBuy(LocalDateTime dateTicketBuy) {
        this.dateTicketBuy = dateTicketBuy;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
