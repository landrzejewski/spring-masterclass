package pl.training.bank.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "addresses")
@Entity
@Data
public class Address implements Serializable {

    @GeneratedValue
    @Id
    private Long id;
    @Column(name = "base_info")
    private String baseInfo;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;
    private String country;

}
