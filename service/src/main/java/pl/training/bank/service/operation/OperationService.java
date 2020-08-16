package pl.training.bank.service.operation;

import lombok.Setter;
import pl.training.bank.entity.Operation;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Setter
@Stateless
public class OperationService {

    @EJB
    private JpaOperationRepository operationRepository;

    public void save(Operation operation) {
        operationRepository.save(operation);
    }

}
