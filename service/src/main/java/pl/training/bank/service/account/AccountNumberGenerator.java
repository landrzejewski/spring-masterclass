package pl.training.bank.service.account;

import javax.ejb.Local;

@Local
public interface AccountNumberGenerator {

    String getNext();

    String getLast();

}
