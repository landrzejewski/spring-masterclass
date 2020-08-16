package pl.training.bank.service.operation;

import lombok.Setter;
import lombok.extern.java.Log;
import pl.training.bank.entity.Account;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Log
@Setter
@Startup
@Singleton
public class SchedulerService {

    private static final long INTEREST_VALUE = 1;
    private static final long FEE_VALUE = -3;

    @Resource
    private TimerService timerService;
    @PersistenceContext(unitName = "bank")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setPersistent(false);
        timerConfig.setInfo("Timer info");
        //timerService.createIntervalTimer(new Date(), 1_000 * 60, timerConfig);
        timerService.createCalendarTimer(new ScheduleExpression().hour("*").minute("*/1"));
    }

    @Timeout
    public void onTimeout(Timer timer) {
        entityManager.createNamedQuery(Account.UPDATE_BALANCE)
                .setParameter("value", FEE_VALUE)
                .executeUpdate();
    }

    @Schedule(hour = "*", minute = "*", persistent = false)
    public void updateInterest() {
        entityManager.createNamedQuery(Account.UPDATE_BALANCE)
                .setParameter("value", INTEREST_VALUE)
                .executeUpdate();
    }

}
