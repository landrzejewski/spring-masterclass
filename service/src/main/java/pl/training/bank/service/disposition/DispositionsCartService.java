package pl.training.bank.service.disposition;

import lombok.Setter;
import lombok.extern.java.Log;
import pl.training.bank.api.disposition.DispositionsCart;
import pl.training.bank.api.disposition.DispositionDto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;

@Log
@Setter
@Stateful
public class DispositionsCartService implements DispositionsCart {

    @EJB
    private DispositionExecutor dispositionExecutor;
    private List<DispositionDto> dispositions = new ArrayList<>();

    @Override
    public void add(DispositionDto dispositionDto) {
        dispositions.add(dispositionDto);
    }

    @Remove
    @Override
    public void submit() {
        dispositionExecutor.execute(dispositions);
    }

    @Remove
    @Override
    public void cancel() {
    }

    @PostConstruct
    public void init() {
        log.info("DispositionsCartService is ready...");
    }

    @PreDestroy
    public void destroy() {
        log.info("DispositionsCartService is going down...");
    }

    @PrePassivate
    public void prePassivate() {
        log.info("DispositionsCartService will be passivated...");
    }

    @PostActivate
    public void postActivate() {
        log.info("DispositionsCartService will be activated...");
    }

}
