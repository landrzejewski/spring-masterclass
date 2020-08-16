package pl.training.bank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class BusinessClient extends Client {

    private String name;
    private String tin;

}
