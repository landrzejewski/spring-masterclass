package pl.training.shop.products;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode(exclude = "id")
@Data
public class Product {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String description;
    private long price;

}
