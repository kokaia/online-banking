package ge.softlab.lessons.onlinebanking.repositories;

import ge.softlab.lessons.onlinebanking.entities.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDomain, Integer> {

    Optional<UserDomain> findAllByUsername(String username);

}
