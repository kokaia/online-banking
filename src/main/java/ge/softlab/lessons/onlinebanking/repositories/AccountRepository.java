package ge.softlab.lessons.onlinebanking.repositories;

import ge.softlab.lessons.onlinebanking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByPersonId(Integer personId);
}
