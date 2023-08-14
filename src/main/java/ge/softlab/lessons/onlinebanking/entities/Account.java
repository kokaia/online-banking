package ge.softlab.lessons.onlinebanking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(schema = "public", name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="accounts_seq_generator")
    @SequenceGenerator(name="accounts_seq_generator", sequenceName="accounts_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    private String name;
    private Double amount;
    private String iban;

}
