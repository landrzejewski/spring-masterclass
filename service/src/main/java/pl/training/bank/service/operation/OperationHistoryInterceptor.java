package pl.training.bank.service.operation;

import pl.training.bank.entity.Account;
import pl.training.bank.entity.Operation;
import pl.training.bank.entity.OperationName;
import pl.training.bank.service.account.AccountService;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Date;

public class OperationHistoryInterceptor {

    private static final int FUNDS_INDEX = 0;
    private static final int ACCOUNT_NUMBER_INDEX = 1;

    @EJB
    private AccountService accountService;
    @EJB
    private OperationService operationService;

    @AroundInvoke
    public Object addEntry(InvocationContext invocationContext) throws Exception {
        Object result = invocationContext.proceed();
        Operation operation = mapToOperation(invocationContext);
        operationService.save(operation);
        return result;
    }

    private Operation mapToOperation(InvocationContext invocationContext) {
        String methodName = invocationContext.getMethod().getName();
        Object[] parameters = invocationContext.getParameters();
        Account account = mapToAccount(parameters);
        OperationName operationName = mapToOperationName(methodName);
        long funds = mapToFunds(parameters);
        return createOperation(account, operationName, funds);
    }

    private Account mapToAccount(Object[] parameters) {
        String accountNumber = (String) parameters[ACCOUNT_NUMBER_INDEX];
        return accountService.getAccount(accountNumber);
    }

    private OperationName mapToOperationName(String methodName) {
        return OperationName.valueOf(methodName.toUpperCase());
    }

    private long mapToFunds(Object[] parameters) {
        return (Long) parameters[FUNDS_INDEX];
    }

    private Operation createOperation(Account account, OperationName operationName, long funds) {
        Operation operation = new Operation(account, operationName, funds);
        operation.setDate(new Date());
        return operation;
    }

}
