package pl.training.shop.tokens;

import lombok.*;
import pl.training.shop.common.BaseEntity;

import javax.persistence.*;

@Table(indexes = {
        @Index(name = "token_value_index", columnList = "value", unique = true)
})
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Token extends BaseEntity {

    @Column(unique = true)
    @NonNull
    private String value;
    @Column(name = "owner_id", nullable = false)
    @NonNull
    private Long ownerId;

}
