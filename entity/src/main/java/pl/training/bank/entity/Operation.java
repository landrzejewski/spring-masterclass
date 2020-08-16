package pl.training.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@TableGenerator(name = "operation_id_generator",
    table = "operations_sequence",
    allocationSize = 20
)
@Table(name = "operations")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Operation implements Serializable {

    @GeneratedValue(strategy = GenerationType.TABLE, generator = "operation_id_generator")
    @Id
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToOne
    @NonNull
    private Account account;
    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    @NonNull
    private OperationName operationName;
    @NonNull
    private long funds;

}
