package pl.training.bank.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@DiscriminatorColumn(name = "client_type")
@Table(name = "clients")
@Entity
@Data
public class Client implements Serializable {

    @GeneratedValue
    @Id
    protected Long id;
    @ManyToMany
    protected List<Account> accounts;
    @OneToOne
    protected Address primaryAddress;
    @JoinColumn(name = "client_id")
    @OneToMany
    protected List<Address> addresses;

}
