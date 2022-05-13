package pl.ticketsystem.ticketsystem.PayPal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.net.URISyntaxException;



@RestController
@RequestMapping(path="api/payment")
public class PayPalController {

    @Autowired
    PayPalService payPalService;
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    @PostMapping("/pay")
    public String payment(@RequestBody Order order) {


        try {
            Payment payment = payPalService.createPayment(order.getPrice(), "PLN", "http://localhost:8080/api/payment/"+CANCEL_URL,
                    "http://localhost:8080/api/payment/"+SUCCESS_URL);

            System.out.println(payment.getState());
           Links approval_url = payment
                   .getLinks()
                   .stream()
                   .filter(f -> f.getRel().contains("approval_url"))
                   .findFirst()
                   .orElseThrow();
            System.out.println(approval_url.getHref());
            return approval_url.getHref();
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }




    return null;
    }

    @GetMapping( SUCCESS_URL)
    public RedirectView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws URISyntaxException {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
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
    public String cancelPay() {
        return "#";
    }

}
