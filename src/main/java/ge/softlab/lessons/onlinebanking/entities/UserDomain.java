package ge.softlab.lessons.onlinebanking.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(schema = "public", name = "users")
public class UserDomain implements UserDetails {

    @Id
    private Integer id;

    @Column(name="email")
    private String username;

    private String password;

    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleDomain> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return new ArrayList<>();
        }
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return Objects.equals(active, Boolean.TRUE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return Objects.equals(active, Boolean.TRUE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Objects.equals(active, Boolean.TRUE);
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(active, Boolean.TRUE);
    }
}
