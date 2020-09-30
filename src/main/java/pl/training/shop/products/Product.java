package pl.training.shop.products;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Product extends BaseEntity {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String description;
    private long price;

}
