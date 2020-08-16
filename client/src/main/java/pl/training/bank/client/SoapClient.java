package pl.training.bank.client;

import pl.training.bank.api.Bank;
import pl.training.bank.api.account.AccountDto;
import pl.training.bank.client.soap.BankService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class SoapClient {

    public static void main(String[] args) throws MalformedURLException {
        URL wsdlLocation = new URL("http://localhost:8080/bank/BankService/BankFacade?wsdl");
        QName serviceName = new QName("http://api.bank.training.pl/", "BankService");
        QName portName = new QName("http://api.bank.training.pl/", "Bank");
        Service proxyFactory = Service.create(wsdlLocation, serviceName);
        Bank bank = proxyFactory.getPort(portName, Bank.class);

        AccountDto account = bank.createAccount();
        System.out.println(bank.getAccount(account.getNumber()));

        pl.training.bank.client.soap.Bank otherBank = new BankService().getBank();
        otherBank.createAccount();
        System.out.println(otherBank.getAccount(account.getNumber()));
    }

}
