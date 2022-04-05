package pl.ticketsystem.ticketsystem.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping(path="{paymentId}")
    public Payment getPayment(@PathVariable long paymentId) {
        return paymentService.getPayment(paymentId).get();
    }

    @PostMapping
    public void addPayment(@RequestBody Payment payment) {
        paymentService.addPayment(payment);
    }

    @PutMapping(path="{paymentId}")
    public void updatePayment(@PathVariable long paymentId, @RequestBody Payment payment) {
        paymentService.updatePayment(paymentId, payment);
    }

    @DeleteMapping(path="{paymentId}")
    public void deletePayment(@PathVariable long paymentId) { paymentService.deletePayment(paymentId); }
}
