package pl.training.cloud.authentication;

import lombok.Data;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Data
public class User {

    @GeneratedValue
    @Id
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

}
