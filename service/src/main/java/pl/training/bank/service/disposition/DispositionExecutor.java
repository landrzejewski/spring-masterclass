package pl.training.bank.service.disposition;

import pl.training.bank.api.disposition.DispositionDto;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import java.util.List;

@Stateless
public class DispositionExecutor {

    @Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java:jboss/exported/jms/queue/Bank")
    private Queue dispositionsQueue;

    public void execute(List<DispositionDto> dispositionDtos) {
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            JMSProducer jmsProducer = jmsContext.createProducer();
            dispositionDtos.forEach(dispositionDto -> jmsProducer.send(dispositionsQueue, dispositionDto));
        }
    }

}
