package ge.softlab.lessons.onlinebanking.entities;

import ge.softlab.lessons.onlinebanking.models.SuperAnotaion;
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

    public final String str = "CONST";

    @Id
    private Integer id;

    @SuperAnotaion(value = "asdsa", name = "aa")
    @Column(name="email")
    private String username;

    @SuperAnotaion
    @Column(name="password")
    private String password;

    @SuperAnotaion("true")
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleDomain> roles;

    @SuperAnotaion
    public void printMethod() {
        System.out.println("\n\nHi\n\n");
    }
    @SuperAnotaion
    public void printMethodWithArgument(String a) {
        System.out.println(a);
    }

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
