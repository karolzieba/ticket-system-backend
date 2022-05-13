package pl.ticketsystem.ticketsystem.PayPal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="api/payment")
public class PayPalController {

    @Autowired
    PayPalService payPalService;
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    @PostMapping("/pay")
    public String payment(@RequestBody Order order) {


        try {
            Payment payment = payPalService.createPayment(order.getPrice(), "PLN", "http://localhostL8080/"+CANCEL_URL,
                    "http://localhostL8080/"+SUCCESS_URL);
            for (Links link: payment.getLinks())
            {
                System.out.println("DUPA");
                System.out.println(link.getRel());
                if(link.getRel().equals("approval_url"))
                {
                    System.out.println(link.getHref());
                    return link.getHref();
                }
                else
                {
                    System.out.println("DWUSETKI PALAM");
                    return "#";
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }




        return "#";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "#";
    }

}
