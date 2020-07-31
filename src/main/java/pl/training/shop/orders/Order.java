package pl.training.shop.orders;

import lombok.*;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.payments.LocalMoney;
import pl.training.shop.payments.Payment;
import pl.training.shop.products.Product;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

@Table(name = "orders")
@Entity
@EqualsAndHashCode(exclude = "id")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Order {

    @GeneratedValue
    @Id
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty
    @NonNull
    private List<Product> products;
    @OneToOne(cascade = CascadeType.PERSIST)
    @Valid
    private Payment payment;
    private Instant timestamp;

    public FastMoney getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(LocalMoney.zero(), FastMoney::add);
    }

}
