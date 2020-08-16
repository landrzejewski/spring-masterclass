package pl.training.bank.api.account;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "account")
@Data
public class AccountDto implements Serializable  {

    private String number;
    private long balance;

}
