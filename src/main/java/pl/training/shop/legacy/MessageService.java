package pl.training.shop.legacy;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.jms.annotation.JmsListener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/*@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "ShopDS"),
})*/
@Log
public class MessageService implements MessageListener {

    @JmsListener(destination = "ShopDS")
    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        var text = message.getBody(String.class);
        log.info("Processing new message: " + text);
    }

}
