package pl.training.bank.api.disposition;

import pl.training.bank.api.disposition.DispositionDto;

import javax.ejb.Remote;

@Remote
public interface DispositionsCart {

    void add(DispositionDto dispositionDto);

    void submit();

    void cancel();

}
