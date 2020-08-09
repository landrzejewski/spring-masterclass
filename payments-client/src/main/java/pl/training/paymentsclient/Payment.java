package pl.training.paymentsclient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    @Id
    private String id;
    @NonNull
    private String transactionId;
    @NonNull
    private Long amount;
    @NonNull
    private Instant timestamp;

    boolean hasTransactionId() {
        return !transactionId.isEmpty();
    }

}
