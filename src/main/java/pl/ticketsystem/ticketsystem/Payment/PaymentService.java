package pl.ticketsystem.ticketsystem.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    public Optional<Payment> getPayment(long id) {
        return paymentRepository.findById(id);
    }
    public void addPayment(Payment payment) {
        if(!Objects.isNull(payment.getStartDatePayment()) /*&&
                !Objects.isNull(payment.getTypePayment())*/) {
            paymentRepository.save(payment);
        }
    }

    public void updatePayment(long id, Payment payment) {
        if(paymentRepository.existsById(id)) {
            Payment updatedPayment = paymentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Payment with this ID does not exist!"));

            if(!Objects.isNull(payment.getEndDatePayment())) {
                if(Objects.isNull(updatedPayment.getEndDatePayment())) {
                    updatedPayment.setEndDatePayment(payment.getEndDatePayment());
                }
            }

            paymentRepository.save(updatedPayment);
        }
        else {
            throw new IllegalStateException("Payment with this ID does not exist!");
        }
    }
    public void deletePayment(long id) {
        paymentRepository.deleteById(id);
    }
}
