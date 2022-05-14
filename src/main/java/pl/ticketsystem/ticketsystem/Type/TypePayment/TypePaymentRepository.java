package pl.ticketsystem.ticketsystem.Type.TypePayment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypePaymentRepository extends JpaRepository <TypePayment, Long> {
    Optional<TypePayment> getTypePaymentByIdTypePayment(int typePayment);
}
