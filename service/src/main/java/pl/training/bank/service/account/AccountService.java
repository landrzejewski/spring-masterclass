package pl.training.bank.service.account;

import lombok.Setter;
import pl.training.bank.api.account.InsufficientFundsException;
import pl.training.bank.entity.Account;
import pl.training.bank.service.disposition.DepositLimitInterceptor;
import pl.training.bank.service.operation.OperationHistoryInterceptor;

import javax.ejb.AsyncResult;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.concurrent.Future;

@Setter
@Stateless
public class AccountService {

    @EJB
    private AccountNumberGenerator accountNumberGenerator;
    @EJB(beanName = "JpaAccountRepository")
    private AccountRepository accountRepository;

    public Account createAccount() {
        String accountNumber = accountNumberGenerator.getNext();
        Account account = new Account(accountNumber);
        accountRepository.save(account);
        return account;
    }

    @Interceptors({OperationHistoryInterceptor.class, DepositLimitInterceptor.class})
    public void deposit(long funds, String accountNumber) {
       accountRepository.getByNumber(accountNumber).deposit(funds);
    }

    @Interceptors(OperationHistoryInterceptor.class)
    public void withdraw(long funds, String accountNumber) {
        Account account = accountRepository.getByNumber(accountNumber);
        checkFunds(funds, account);
        account.withdraw(funds);
    }

    private void checkFunds(long funds, Account account) {
        if (account.getBalance() < funds) {
            throw new InsufficientFundsException();
        }
    }

    public Account getAccount(String accountNumber) {
        return accountRepository.getByNumber(accountNumber);
    }

    public Future<Long> getTotalBalance() {
        try {
            Thread.sleep(5_000); // only for training purposes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(accountRepository.getAll().stream()
                .mapToLong(Account::getBalance)
                .sum());
    }

    public Account getAccount(Long id) {
        return accountRepository.getById(id);
    }

}
