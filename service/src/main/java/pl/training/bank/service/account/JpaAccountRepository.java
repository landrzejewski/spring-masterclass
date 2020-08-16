package pl.training.bank.service.account;

import lombok.Setter;
import pl.training.bank.api.account.AccountNotFoundException;
import pl.training.bank.entity.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Setter
@Stateless
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext(unitName = "bank")
    private EntityManager entityManager;

    @Override
    public Account getById(Long id) {
        Account account = entityManager.find(Account.class, id);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    @Override
    public Account getByNumber(String number) {
        try {
            return entityManager.createNamedQuery(Account.GET_BY_NUMBER, Account.class)
                    .setParameter("number", number)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public List<Account> getAll() {
        return entityManager.createNamedQuery(Account.GET_ALL, Account.class)
                .getResultList();
    }

    @Override
    public void update(Account account) {
        getById(account.getId());
        entityManager.merge(account);
    }

    @Override
    public Account save(Account account) {
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }

}
