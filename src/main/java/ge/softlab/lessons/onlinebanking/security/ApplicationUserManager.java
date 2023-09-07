package ge.softlab.lessons.onlinebanking.security;

import ge.softlab.lessons.onlinebanking.entities.UserDomain;
import ge.softlab.lessons.onlinebanking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class ApplicationUserManager implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDomain> users = userRepository.findAllByUsername(username);
        if (users.isEmpty()){
           throw new RuntimeException("User %s not found".formatted(username));
        }
        return users.get();
    }

    public static UserDomain getCurrentUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new RuntimeException("auth needed");
        }
        if (auth.getPrincipal() instanceof UserDomain user){
            return user;
        }
        throw new RuntimeException("Anonymus");
    }

}
