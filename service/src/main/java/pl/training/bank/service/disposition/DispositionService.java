package pl.training.bank.service.disposition;

import lombok.Setter;
import lombok.extern.java.Log;
import pl.training.bank.api.disposition.DispositionDto;
import pl.training.bank.entity.OperationName;
import pl.training.bank.service.account.AccountService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Log
@Setter
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "BankDS"),
})
public class DispositionService implements MessageListener {

    @EJB
    private AccountService accountService;

    @Override
    public void onMessage(Message message) {
        try {
            DispositionDto dispositionDto = message.getBody(DispositionDto.class);
            log.info("Processing new disposition: " + dispositionDto);
            switch (OperationName.valueOf(dispositionDto.getOperationName())) {
                case DEPOSIT:
                    accountService.deposit(dispositionDto.getFunds(), dispositionDto.getAccountNumber());
                    break;
                case WITHDRAW:
                    accountService.withdraw(dispositionDto.getFunds(), dispositionDto.getAccountNumber());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation name");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        log.info("DispositionService is ready...");
    }

    @PreDestroy
    public void destroy() {
        log.info("DispositionService is going down...");
    }

}
