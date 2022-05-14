package pl.ticketsystem.ticketsystem.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ticketsystem.ticketsystem.Type.TypePayment.TypePayment;

import java.time.LocalDateTime;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT idPayment FROM Payment WHERE startDatePayment = :date AND typePayment = :type")
    long findIdPaymentByPaymentDateAndType(@Param("date") LocalDateTime date, @Param("type") TypePayment type);
}
