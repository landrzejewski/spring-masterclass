package pl.training.bank.service;

import lombok.Setter;
import pl.training.bank.api.BankAsync;
import pl.training.bank.service.account.AccountService;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

@Setter
@Stateless
public class BankFacadeAsync implements BankAsync  {

    @EJB
    private AccountService accountService;

    @Asynchronous
    @Override
    public Future<Long> getTotalBalance() {
        return accountService.getTotalBalance();
    }

}
