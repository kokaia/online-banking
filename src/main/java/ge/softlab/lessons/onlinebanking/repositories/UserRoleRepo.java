package ge.softlab.lessons.onlinebanking.repositories;

import ge.softlab.lessons.onlinebanking.entities.UserRoleDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepo extends JpaRepository<UserRoleDomain, Integer> {
    List<UserRoleDomain> findAllByUserId(Integer userId);

}
