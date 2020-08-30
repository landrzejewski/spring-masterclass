package pl.training.products;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String description;
    private long price;

}
