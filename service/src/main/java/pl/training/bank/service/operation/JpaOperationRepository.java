package pl.training.bank.service.operation;

import lombok.Setter;
import pl.training.bank.entity.Operation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Setter
@Stateless
public class JpaOperationRepository {

    @PersistenceContext(unitName = "bank")
    private EntityManager entityManager;

    public void save(Operation operation) {
        entityManager.persist(operation);
    }

}
