package pl.ticketsystem.ticketsystem.Ticket;

import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Event.Event;
import pl.ticketsystem.ticketsystem.Payment.Payment;
import pl.ticketsystem.ticketsystem.TypeOfEvent.TypeEvent;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;
    private Date dateTicketBuy;

   @ManyToOne
   @JoinColumn(name="idClientFK", referencedColumnName = "idClient")
    private Client client;


    @ManyToOne
    @JoinColumn(name="idEventFK", referencedColumnName = "idEvent")
    private Event event;

    @ManyToOne
    @JoinColumn(name="idPaymentFK", referencedColumnName = "idPayment")
    private Payment payment;

}
