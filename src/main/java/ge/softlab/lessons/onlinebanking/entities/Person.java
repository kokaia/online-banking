package ge.softlab.lessons.onlinebanking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "persons")
public class Person {

    @Id
    private Integer id;

    @Column(name="personal_number")
    private String personalNumber;

    @Column(name="first_name")
    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private List<Account> accounts;

    public Integer getAge() {
        if (this.birthDate == null) {
            return null;
        }
        return LocalDate.now().getYear() - this.birthDate.getYear();
    }

}
