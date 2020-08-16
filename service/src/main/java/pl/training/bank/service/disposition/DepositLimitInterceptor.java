package pl.training.bank.service.disposition;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Log
public class DepositLimitInterceptor {

    private static final long DEPOSIT_LIMIT = 1_000;
    private static final int FUNDS_INDEX = 0;
    private static final int ACCOUNT_NUMBER_INDEX = 1;

    @Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java:jboss/exported/jms/topic/Bank")
    private Topic topic;

    @AroundInvoke
    public Object checkDepositLimit(InvocationContext invocationContext) throws Exception {
        log.info("Checking deposit limit...");
        Object result = invocationContext.proceed();
        Object[] parameters = invocationContext.getParameters();
        long funds = (Long) parameters[FUNDS_INDEX];
        String accountNumber = (String) parameters[ACCOUNT_NUMBER_INDEX];
        String message = "Deposit limit on account: " + accountNumber;
        if (funds > DEPOSIT_LIMIT) {
            try (JMSContext jmsContext = connectionFactory.createContext()) {
                jmsContext.createProducer().send(topic, message);
            }
        }
        return result;
    }

}
