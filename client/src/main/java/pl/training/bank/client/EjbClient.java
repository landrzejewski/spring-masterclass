package pl.training.bank.client;

import pl.training.bank.api.Bank;
import pl.training.bank.api.BankAsync;
import pl.training.bank.api.disposition.DispositionsCart;
import pl.training.bank.api.account.AccountDto;
import pl.training.bank.api.disposition.DispositionDto;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.NamingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EjbClient {

    private static final String BANK_JNDI_NAME = "java:/bank/BankFacade!pl.training.bank.api.Bank";
    private static final String BANK_ASYNC_JNDI_NAME = "java:/bank/BankFacadeAsync!pl.training.bank.api.BankAsync";
    private static final String DISPOSITIONS_CART_JNDI_NAME = "java:/bank/DispositionsCartService!pl.training.bank.api.disposition.DispositionsCart";
    private static final String QUEUE_CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String BANK_QUEUE_JNDI_NAME = "jms/queue/Bank";

    public static void main(String[] args) throws NamingException, InterruptedException, ExecutionException {
        ProxyFactory proxyFactory = new ProxyFactory();
        Bank bank = proxyFactory.createProxy(BANK_JNDI_NAME);
        BankAsync bankAsync = proxyFactory.createProxy(BANK_ASYNC_JNDI_NAME);
        ConnectionFactory connectionFactory = proxyFactory.createProxy(QUEUE_CONNECTION_FACTORY_JNDI_NAME);
        Queue dispositionsQueue = proxyFactory.createProxy(BANK_QUEUE_JNDI_NAME);
        DispositionsCart dispositionsCart = proxyFactory.createProxy(DISPOSITIONS_CART_JNDI_NAME);

        AccountDto accountDto = bank.createAccount();
        DispositionDto dispositionDto = new DispositionDto(accountDto.getNumber(), 1_001, "DEPOSIT");

        try (JMSContext jmsContext = connectionFactory.createContext()) {
            jmsContext.createProducer().send(dispositionsQueue, dispositionDto);
        }

        dispositionsCart.add(dispositionDto);
        dispositionsCart.add(new DispositionDto(accountDto.getNumber(), 500, "WITHDRAW"));
        dispositionsCart.submit();

        Thread.sleep(5_000);

        System.out.println(bank.getAccount(accountDto.getNumber()));

        Future<Long> totalBalance = bankAsync.getTotalBalance();
        System.out.println("Report is done: " + totalBalance.isDone());
        System.out.println("Total balance: " + totalBalance.get());
    }

}
