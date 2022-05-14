package pl.ticketsystem.ticketsystem.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ticketsystem.ticketsystem.Type.TypePayment.TypePayment;
import pl.ticketsystem.ticketsystem.Type.TypePayment.TypePaymentRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final TypePaymentRepository typePaymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, TypePaymentRepository typePaymentRepository) {
        this.paymentRepository = paymentRepository;
        this.typePaymentRepository = typePaymentRepository;
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    public Optional<Payment> getPayment(long id) {
        return paymentRepository.findById(id);
    }
    public long addPayment(Payment payment) {
        if(!Objects.isNull(payment.getStartDatePayment()) /*&&
                !Objects.isNull(payment.getTypePayment())*/) {

            TypePayment typePayment = typePaymentRepository.getTypePaymentByIdTypePayment(payment.getTypePayment().getIdTypePayment())
                            .orElseThrow(() -> new IllegalStateException("Type of payment with this ID does not exist!"));
            payment.setTypePayment(typePayment);
            paymentRepository.save(payment);
        }

        return paymentRepository.findIdPaymentByPaymentDateAndType(payment.getStartDatePayment(), payment.getTypePayment());
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
