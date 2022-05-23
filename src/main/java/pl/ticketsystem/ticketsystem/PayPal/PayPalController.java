package pl.ticketsystem.ticketsystem.PayPal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.ticketsystem.ticketsystem.Client.Client;
import pl.ticketsystem.ticketsystem.Client.ClientRepository;
import pl.ticketsystem.ticketsystem.Event.Event;
import pl.ticketsystem.ticketsystem.Event.EventRepository;
import pl.ticketsystem.ticketsystem.Payment.PaymentRepository;
import pl.ticketsystem.ticketsystem.Ticket.Ticket;
import pl.ticketsystem.ticketsystem.Ticket.TicketRepository;
import pl.ticketsystem.ticketsystem.Type.TypePayment.TypePayment;
import pl.ticketsystem.ticketsystem.Type.TypePayment.TypePaymentRepository;

import java.net.URISyntaxException;
import java.time.LocalDateTime;


@RestController
@RequestMapping(path="api/payment")
public class PayPalController {


    private final TypePaymentRepository typePaymentRepository;
    private final TicketRepository ticketRepository;
    private final ClientRepository clientRepository;
    private final EventRepository eventRepository;
    private final PaymentRepository paymentRepository;


    @Autowired
    PayPalService payPalService;
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    public PayPalController(TypePaymentRepository typePaymentRepository, TicketRepository ticketRepository, ClientRepository clientRepository, EventRepository eventRepository, PaymentRepository paymentRepository) {
        this.typePaymentRepository = typePaymentRepository;
        this.ticketRepository = ticketRepository;
        this.clientRepository = clientRepository;
        this.eventRepository = eventRepository;

        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/pay")
    public String payment(@RequestBody Order order) {

        String inline = "/?date="+order.getLocalDateTime();
        try {
            Payment payment = payPalService.createPayment(order.getPrice(), "PLN", order.getDescription(), "http://localhost:8080/api/payment/"+CANCEL_URL,
                    "http://localhost:8080/api/payment/"+SUCCESS_URL+inline);

            System.out.println(payment.getState());
           Links approval_url = payment
                   .getLinks()
                   .stream()
                   .filter(f -> f.getRel().contains("approval_url"))
                   .findFirst()
                   .orElseThrow();

            return approval_url.getHref();
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }




    return null;
    }

    @GetMapping( value = SUCCESS_URL)
    public RedirectView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) throws URISyntaxException {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            TypePayment typePayment = typePaymentRepository.getTypePaymentByIdTypePayment(1).get();

            if (payment.getState().equals("approved")) {
                String dateFormat = payment.getCreateTime().substring(0,16 );

                LocalDateTime aLDT = LocalDateTime.parse(dateFormat);
                pl.ticketsystem.ticketsystem.Payment.Payment payment1 = new pl.ticketsystem.ticketsystem.Payment.Payment(aLDT, date, typePayment);
                paymentRepository.save(payment1);
                String info[] = payment.getTransactions().get(0).getDescription().split(","); // 0 -- idEvent 1 -- idRole

                Event event = eventRepository.getById(Long.valueOf(info[0]));
                Client client = clientRepository.getById(Long.valueOf(info[1]));

                Ticket ticket = new Ticket(aLDT, client, event, payment1);
                ticketRepository.save(ticket);

                event.setCapacityEvent(event.getCapacityEvent() - 1);
                eventRepository.save(event);

                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("http://localhost:3000");
                return redirectView;
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping(value = CANCEL_URL)
    public RedirectView cancelPay() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000");
        return redirectView;
    }

}
