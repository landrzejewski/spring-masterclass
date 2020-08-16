package pl.training.bank.api;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BankException extends RuntimeException {
}
