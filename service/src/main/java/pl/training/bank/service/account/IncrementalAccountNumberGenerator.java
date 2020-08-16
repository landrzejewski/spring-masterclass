package pl.training.bank.service.account;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;

@Log
//@ConcurrencyManagement(ConcurrencyManagementType.BEAN) Disables automatic synchronization of singleton methods
@Startup // Automatic start during deployment
@Singleton
public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private static final String ACCOUNT_NUMBER_FORMAT = "%026d";

    private long counter;

    // @Lock(LockType.WRITE) by default
    @Override
    public String getNext() {
        return format(++counter);
    }

    @Lock(LockType.READ)
    @Override
    public String getLast() {
        return format(counter);
    }

    private String format(long value) {
        return String.format(ACCOUNT_NUMBER_FORMAT, value);
    }

    @PostConstruct
    public void init() {
        log.info("IncrementalAccountNumberGenerator is ready...");
    }

    @PreDestroy
    public void destroy() {
        log.info("IncrementalAccountNumberGenerator is going down...");
    }

}
