package pl.training.shop.mails;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Log
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final RabbitTemplate jmsTemplate;
    private final Queue sendEmailQueue;

    @Value("${email-sender}")
    @Setter
    private String sender;

    public void send(MailMessage mailMessage) {
        jmsTemplate.convertAndSend(sendEmailQueue.getName(), mailMessage);
    }

    @RabbitListener(queues = "${send-email-queue}")
    @SneakyThrows
    public void onMessage(MailMessage message) {
        var messagePreparator = createMimeMessagePreparator(message);
        mailSender.send(messagePreparator);
    }

    private MimeMessagePreparator createMimeMessagePreparator(MailMessage message) {
        return mimeMessage -> {
            var messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(sender);
            messageHelper.setTo(message.getRecipient());
            messageHelper.setSubject(message.getSubject());
            messageHelper.setText(message.getText(), true);
        };
    }

}
