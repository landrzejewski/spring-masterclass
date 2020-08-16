package pl.training.bank.api;

import pl.training.bank.api.account.AccountDto;

import javax.ejb.Remote;
import javax.jws.WebService;


@WebService(portName = "Bank", serviceName = "BankService")
@Remote
public interface Bank {

    AccountDto createAccount();

    AccountDto getAccount(String accountNumber);

}
