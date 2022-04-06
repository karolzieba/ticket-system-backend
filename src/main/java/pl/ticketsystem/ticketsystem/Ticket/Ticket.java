package pl.ticketsystem.ticketsystem.Ticket;

import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Event.Event;
import pl.ticketsystem.ticketsystem.Payment.Payment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTicket;
    private LocalDateTime dateTicketBuy;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="idClientFK", referencedColumnName = "idClient")
    private Client client;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="idEventFK", referencedColumnName = "idEvent")
    private Event event;

    @ManyToOne(cascade = {CascadeType.ALL})
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
