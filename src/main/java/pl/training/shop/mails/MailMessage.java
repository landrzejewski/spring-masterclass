package pl.training.shop.mails;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailMessage implements Serializable {

    @NonNull
    private String recipient;
    @NonNull
    private String subject;
    @NonNull
    private String text;

}
