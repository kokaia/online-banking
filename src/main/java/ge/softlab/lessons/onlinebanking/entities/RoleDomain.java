package ge.softlab.lessons.onlinebanking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@Entity
@Table(schema = "public", name = "roles")
public class RoleDomain implements GrantedAuthority {

    @Id
    private Integer id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

}
