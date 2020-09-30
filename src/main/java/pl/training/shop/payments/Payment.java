package pl.training.shop.payments;

import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.TypeDef;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.common.FastMoneyUserType;

import javax.persistence.*;
import java.time.Instant;

@TypeDef(name = "fastMoney", typeClass = FastMoneyUserType.class, defaultForType = FastMoney.class)
@Table(name = "payments", indexes = @Index(name = "payment_status", columnList = "status"))
@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    @Columns(columns = {
            @Column(name = "currency", length = 3),
            @Column(name = "value")
    })
    //@Type(type = "fastMoney")
    private FastMoney money;
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Override
    public boolean equals(Object otherPayment) {
        if (this == otherPayment) {
            return true;
        }
        if (!(otherPayment instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) otherPayment;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
