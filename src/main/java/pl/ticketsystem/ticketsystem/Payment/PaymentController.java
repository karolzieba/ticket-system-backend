package pl.ticketsystem.ticketsystem.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('payment_show_many')")
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping(path="{paymentId}")
    @PreAuthorize("hasAuthority('payment_show_many')")
    public Payment getPayment(@PathVariable long paymentId) {
        return paymentService.getPayment(paymentId).orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void addPayment(@RequestBody Payment payment) {
        paymentService.addPayment(payment);
    }

    @PatchMapping(path="{paymentId}")
    @PreAuthorize("hasAuthority('payment_update')")
    public void updatePayment(@PathVariable long paymentId, @RequestBody Payment payment) {
        paymentService.updatePayment(paymentId, payment);
    }

    @DeleteMapping(path="{paymentId}")
    @PreAuthorize("hasAuthority('payment_delete')")
    public void deletePayment(@PathVariable long paymentId) { paymentService.deletePayment(paymentId); }
}
