package pl.training.bank.api;

import javax.ejb.Remote;
import java.util.concurrent.Future;

@Remote
public interface BankAsync {

    Future<Long> getTotalBalance();

}
