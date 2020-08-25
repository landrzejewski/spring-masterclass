package pl.training.shop.users;

import lombok.*;
import pl.training.shop.common.validator.Name;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Table(name = "users", indexes = @Index(name = "email", columnList = "email"))
@Entity
@Builder
@EqualsAndHashCode(exclude = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @GeneratedValue
    @Id
    private Long id;
    @Pattern(regexp = "[A-Za-z]+")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Name
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private boolean enabled;

}
