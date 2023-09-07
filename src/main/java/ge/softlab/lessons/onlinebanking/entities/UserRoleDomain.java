package ge.softlab.lessons.onlinebanking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(schema = "public", name = "user_roles")
public class UserRoleDomain {

    @Id
    private Integer id;

    private Integer userId;

    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleDomain role;

}
